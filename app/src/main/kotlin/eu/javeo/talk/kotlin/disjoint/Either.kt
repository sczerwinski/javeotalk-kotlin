package eu.javeo.talk.kotlin.disjoint

abstract class Either<L, R> private constructor() {

	class Left<L>(val value: L) : Either<L, Nothing>() {
		override fun toString() = "Left($value)"
		override fun equals(other: Any?): Boolean = other is Left<*> && other.value == value
		override fun hashCode(): Int = value?.hashCode() ?: 0
	}

	class Right<R>(val value: R) : Either<Nothing, R>() {
		override fun toString() = "Right($value)"
		override fun equals(other: Any?): Boolean = other is Right<*> && other.value == value
		override fun hashCode(): Int = value?.hashCode() ?: 0
	}
}
