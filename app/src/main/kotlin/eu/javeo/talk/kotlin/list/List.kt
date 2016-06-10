package eu.javeo.talk.kotlin.list

import java.util.NoSuchElementException

interface List<T> {
	fun isEmpty(): Boolean
	val head: T
	val tail: List<T>
}

class Cons<T>(override val head: T, override val tail: List<T>) : List<T> {
	override fun isEmpty() = false
}

// TODO: Nil should be an object
class Nil<T> : List<T> {
	override fun isEmpty() = true
	override val head: Nothing get() = throw NoSuchElementException()
	override val tail: Nothing get() = throw NoSuchElementException()
}
