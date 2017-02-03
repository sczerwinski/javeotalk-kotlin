package eu.javeo.talk.delegation.model

class Person {
	var name: String = ""
	var surname: String = ""
	var pesel: String = "00000000000" // TODO: Only accept 11 digits

	override fun toString(): String =
			"$name $surname, PESEL: $pesel"
}
