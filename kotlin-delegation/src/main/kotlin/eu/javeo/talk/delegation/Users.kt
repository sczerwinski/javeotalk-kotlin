package eu.javeo.talk.delegation

import eu.javeo.talk.delegation.model.User
import java.util.*

fun main(args: Array<String>) {
	val settings = Settings.load()
	val user = User(settings)
	println(user)
}

object Settings {

	fun load(): Map<Any, Any> {
		val settings = Properties()
		val input = javaClass.classLoader.getResourceAsStream("settings.properties")
		settings.load(input)
		input.close()
		return settings
	}
}
