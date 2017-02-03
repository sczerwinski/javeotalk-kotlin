package eu.javeo.talk.glimpse

import glimpse.Color
import glimpse.Point
import glimpse.Vector
import glimpse.cameras.camera
import glimpse.cameras.perspective
import glimpse.cameras.targeted
import glimpse.degrees
import glimpse.io.resource
import glimpse.jogl.glimpseFrame
import glimpse.lights.Light
import glimpse.lights.directionLight
import glimpse.materials.Plastic
import glimpse.materials.Textured
import glimpse.models.loadObjMeshes
import glimpse.models.sphere
import glimpse.textures.Texture
import glimpse.textures.loadTexture
import java.util.*

object Context

fun main(args: Array<String>) {

	val lights = listOf<Light>(directionLight {
		direction { Vector(-1f, -1f, -0.5f) }
	})

	var aspect = 1f
	val camera = camera {
		perspective {
			fov { 30.degrees }
			aspect { aspect }
			distanceRange(1f to 10f)
		}
		targeted {
			position {
				Vector(5f, 90.degrees, 0.degrees).toPoint()
			}
			target { Point.ORIGIN }
			up { Vector.Z_UNIT }
		}
	}

	val model = sphere(16).transform {
		val time = (Date().time / 30) % 360
		rotateZ(time.degrees)
		rotateX(-23.5.degrees)
	}

	val obj = Context.resource("/mesh.obj").loadObjMeshes().map {
		it.transform {
			scale(0.3f)
			rotateY(-90.degrees)
			rotateZ(180.degrees)
			translate(Vector(1.5f, 0f, -0.1f))
		}
	}
	val textures = mutableMapOf<Textured.TextureType, Texture>()
	val texturedMaterial = Textured { type -> textures[type]!! }
	val whitePlastic = Plastic(Color.WHITE)
	val redPlastic = Plastic(Color.RED)

	glimpseFrame("JaveoTalk Glimpse") {
		onInit {
			clearColor = Color.BLACK
			isDepthTest = true
			textures[Textured.TextureType.AMBIENT] =
					Context.resource("/ambient.png").loadTexture { withMipmap() }
			textures[Textured.TextureType.DIFFUSE] =
					Context.resource("/diffuse.png").loadTexture { withMipmap() }
			textures[Textured.TextureType.SPECULAR] =
					Context.resource("/specular.png").loadTexture { withMipmap() }
		}
		onResize { v ->
			viewport = v
			aspect = v.aspect
		}
		onRender {
			clearColorBuffer()
			clearDepthBuffer()
			texturedMaterial.render(model, camera, lights)
			redPlastic.render(obj[0], camera, lights)
			whitePlastic.render(obj[1], camera, lights)
		}
		onDispose {
		}
	}
}
