package eu.javeo.talk.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		/* TODO: Implement dialog functions
		dialog {
			title { R.string.question_dialog_title }
			message { R.string.question_dialog_message }
			button {
				text { R.string.button_no }
				onClick { onNoButton() }
			}
			button {
				text { R.string.button_yes }
				onClick { onYesButton() }
			}
		}
		*/
	}

	fun onYesButton() {
		Toast.makeText(this, "“Yes” button was clicked", Toast.LENGTH_SHORT).show()
	}

	fun onNoButton() {
		Toast.makeText(this, "“No” button was clicked", Toast.LENGTH_SHORT).show()
	}
}
