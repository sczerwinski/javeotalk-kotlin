package eu.javeo.talk.glimpse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import glimpse.Color
import glimpse.android.GlimpseView
import glimpse.android.glimpseView
import glimpse.cameras.camera
import glimpse.cameras.targeted
import glimpse.degrees
import glimpse.gles.Disposables
import glimpse.materials.Textured
import glimpse.models.sphere
import glimpse.textures.Texture
import glimpse.Vector
import glimpse.android.io.asset
import glimpse.cameras.perspective
import glimpse.lights.directionLight
import glimpse.materials.Plastic
import glimpse.models.Model
import glimpse.models.loadObjMeshes
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import java.util.*
class MainActivity : AppCompatActivity() {

	lateinit var glimpseView: GlimpseView

	var aspect: Float = 1f

	val camera = camera {
		targeted { position { Vector(5f, 0f, 0f).toPoint() } }
		perspective {
			fov { 45.degrees }
			aspect { aspect }
			distanceRange(1f to 10f)
		}
	}

	val lights = listOf(directionLight {
		direction { Vector(-1f, -1f, 0f) }
	})

	val earth = sphere(12).transform {
		val time = (Date().time / 50L) % 360L
		rotateZ(time.degrees)
		rotateX(-23.5.degrees)
	}

	val javeo: List<Model> by lazy {
		assets.open("javeo.obj").loadObjMeshes().map {
			it.transform {
				scale(.3f)
				rotateY(-90.degrees)
				rotateZ(180.degrees)
				translate(Vector(1.5f, 0f, -0.1f))
			}
		}
	}

	val textures = mutableMapOf<Textured.TextureType, Texture>()

	val texturedMaterial = Textured { textures[it]!! }
	val redPlasticMaterial = Plastic(Color.RED)
	val whitePlasticMaterial = Plastic(Color(.9f, .9f, .9f))

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		relativeLayout {
			glimpseView = glimpseView {
				onInit {
					clearColor = Color.BLACK
					isDepthTest = true
					textures[Textured.TextureType.AMBIENT] = asset("ambient.png").loadTexture { withMipmap() }
					textures[Textured.TextureType.DIFFUSE] = asset("diffuse.png").loadTexture { withMipmap() }
					textures[Textured.TextureType.SPECULAR] = asset("specular.png").loadTexture { withMipmap() }
				}
				onResize { v ->
					viewport = v
					aspect = v.aspect
				}
				onRender {
					clearColorBuffer()
					clearDepthBuffer()
					texturedMaterial.render(earth, camera, lights)
					redPlasticMaterial.render(javeo[0], camera, lights)
					whitePlasticMaterial.render(javeo[1], camera, lights)
				}
			}.lparams(width = matchParent, height = matchParent)
		}
	}

	override fun onResume() {
		super.onResume()
		glimpseView.onResume()
	}

	override fun onPause() {
		super.onPause()
		glimpseView.onPause()
	}

	override fun onDestroy() {
		super.onDestroy()
		Disposables.disposeAll()
	}
}

