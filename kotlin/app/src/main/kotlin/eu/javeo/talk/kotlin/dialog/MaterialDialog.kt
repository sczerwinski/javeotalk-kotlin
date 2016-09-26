package eu.javeo.talk.kotlin.dialog

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import eu.javeo.talk.kotlin.R

class MaterialDialog(context: Context) : Dialog(context) {

	// TODO: title and message properties

	// TODO: views (lazy)

	init {
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		setContentView(R.layout.dialog)
		setCancelable(false)
	}

	private fun addButton(button: Button, layout: ViewGroup) {
		layout.addView(button, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
	}

	class Button(context: Context) : android.widget.Button(context) {

	}
}
