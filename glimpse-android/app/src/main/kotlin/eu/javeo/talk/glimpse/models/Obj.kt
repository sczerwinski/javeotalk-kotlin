package eu.javeo.talk.glimpse.models

import glimpse.Point
import glimpse.TextureCoordinates
import glimpse.Vector
import glimpse.models.Mesh
import glimpse.models.Vertex
import java.io.BufferedReader
import java.io.InputStream

internal class MeshBuilder {

	private val positions = mutableListOf<Point>()
	private val textureCoordinates = mutableListOf<TextureCoordinates>()
	private val normals = mutableListOf<Vector>()

	internal fun build() = Mesh(positions.toList(), textureCoordinates.toList(), normals.toList())

	internal fun push(vertex: Vertex) {
		positions.add(vertex.position)
		textureCoordinates.add(vertex.textureCoordinates)
		normals.add(vertex.normal)
	}

	private fun push(vararg vertices: Vertex) {
		vertices.forEach { push(it) }
	}

	/**
	 * Adds a triangular face to the [Mesh].
	 */
	fun triangle(v1: Vertex, v2: Vertex, v3: Vertex) {
		push(v1, v2, v3)
	}

	/**
	 * Adds a quadrilateral face to the [Mesh].
	 */
	fun quad(v1: Vertex, v2: Vertex, v3: Vertex, v4: Vertex) {
		triangle(v1, v2, v3)
		triangle(v3, v4, v1)
	}
}

internal class ObjMeshBuilder(lines: List<String>) {

	private val words = lines.map { it.trim().split(" ", "\t") }

	private val positions: List<Point> by lazy {
		words.filter { it.isPositionLine }.map { it.parsePoint() }
	}

	private val textureCoordinates: List<TextureCoordinates> by lazy {
		words.filter { it.isTextureCoordinatesLine }.map { it.parseTextureCoordinates() }
	}

	private val normals: List<Vector> by lazy {
		words.filter { it.isNormalLine }.map { it.parseVector() }
	}

	private val List<String>.isCommentLine: Boolean
		get() = first() == "#"

	private val List<String>.isNextMeshLine: Boolean
		get() = first() == "o"

	private val List<String>.isPositionLine: Boolean
		get() = first() == "v"

	private val List<String>.isTextureCoordinatesLine: Boolean
		get() = first() == "vt"

	private val List<String>.isNormalLine: Boolean
		get() = first() == "vn"

	private val List<String>.isFaceLine: Boolean
		get() = first() == "f"

	private fun List<String>.parsePoint(): Point =
			Point(this[1].toFloat(), this[2].toFloat(), this[3].toFloat())

	private fun List<String>.parseTextureCoordinates(): TextureCoordinates =
			TextureCoordinates(this[1].toFloat(), this[2].toFloat())

	private fun List<String>.parseVector(): Vector =
			Vector(this[1].toFloat(), this[2].toFloat(), this[3].toFloat())

	private fun String.parseVertex(): Vertex {
		val indices = this.split("/").map {
			if(it.isBlank()) -1
			else it.toInt() - 1
		}
		return Vertex(positions(indices[0]), textureCoordinates(indices[1]), normals(indices[2]))
	}

	private fun positions(index: Int): Point =
			if (index < 0) Point.ORIGIN
			else positions[index]

	private fun textureCoordinates(index: Int): TextureCoordinates =
			if (index < 0) TextureCoordinates.BOTTOM_LEFT
			else textureCoordinates[index]

	private fun normals(index: Int): Vector =
			if (index < 0) Vector.NULL
			else normals[index]

	internal fun build(): List<Mesh> {
		val list = mutableListOf<Mesh>()
		var builder = MeshBuilder()
		words.filter { it.isNextMeshLine || it.isFaceLine }.dropWhile { it.isNextMeshLine }.forEach {
			when {
				it.isNextMeshLine -> {
					list.add(builder.build())
					builder = MeshBuilder()
				}
				it.isFaceLine -> it.drop(1).map { it.parseVertex() }.forEach { builder.push(it) }
			}
		}
		list.add(builder.build())
		return list
	}
}

fun lines(inputStream: InputStream): List<String> {
	tailrec fun readLines(reader: BufferedReader, lines: List<String>): List<String> {
		val line = reader.readLine()
		return if (line == null) lines else readLines(reader, lines + line)
	}
	val reader = inputStream.bufferedReader()
	val lines = readLines(reader, emptyList())
	reader.close()
	return lines
}

fun InputStream.loadObjMesh(): List<Mesh> = ObjMeshBuilder(lines(this)).build()
