package eu.javeo.talk.glimpse

import glimpse.Color
import glimpse.jogl.glimpseFrame

fun main(args: Array<String>) {
	glimpseFrame("JaveoTalk Glimpse") {
		onInit {
			clearColor = Color.BLACK
			isDepthTest = true
		}
		onResize { v ->
			viewport = v
		}
		onRender {
			clearColorBuffer()
			clearDepthBuffer()
		}
		onDispose {
		}
	}
}
