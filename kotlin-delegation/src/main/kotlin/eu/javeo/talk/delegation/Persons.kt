package eu.javeo.talk.delegation

import eu.javeo.talk.delegation.model.Person

fun main(args: Array<String>) {
	val person = Person()
	person.name = "John"
	person.surname = "Smith"
	person.pesel = "1234567890"
	println(person)
	person.pesel = "1234567890A"
	println(person)
	person.pesel = "12345678901"
	println(person)
}
