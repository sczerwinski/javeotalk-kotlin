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
import glimpse.textures.mipmap
import glimpse.textures.readTexture
import glimpse.Vector
import glimpse.cameras.perspective
import glimpse.lights.Light
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import java.util.*

class MainActivity : AppCompatActivity() {

	lateinit var glimpseView: GlimpseView

	var aspect: Float = 1f

	val camera = camera {
		targeted { position { Vector(5f, 0f, 0f).toPoint() } }
		perspective {
			fov { 60.degrees }
			aspect { aspect }
			distanceRange(1f to 10f)
		}
	}

	val lights = listOf(Light.DirectionLight(Vector(-1f, -1f, 0f)))

	val earth = sphere(16).transform {
		val time = (Date().time / 50L) % 360L
		rotateZ(time.degrees)
		rotateX(-23.5.degrees)
	}

	val textures = mutableMapOf<Textured.TextureType, Texture>()

	val texturedMaterial = Textured { textures[it]!! }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		relativeLayout {
			glimpseView = glimpseView {
				onInit {
					clearColor = Color.BLACK
					isDepthTest = true
					textures[Textured.TextureType.AMBIENT] = assets.open("ambient.png").readTexture { name = "ambient.png" with mipmap }
					textures[Textured.TextureType.DIFFUSE] = assets.open("diffuse.png").readTexture { name = "diffuse.png" with mipmap }
					textures[Textured.TextureType.SPECULAR] = assets.open("specular.png").readTexture { name = "specular.png" with mipmap }
				}
				onResize { v ->
					viewport = v
					aspect = v.aspect
				}
				onRender {
					clearColorBuffer()
					clearDepthBuffer()
					texturedMaterial.render(earth, camera, lights)
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
