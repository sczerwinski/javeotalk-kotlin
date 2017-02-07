package eu.javeo.talk.delegation.model

class User(map: Map<Any, Any>) {
	val username: String by map
	val password: String by map

	override fun toString(): String =
			"User($username, $password)"
}
