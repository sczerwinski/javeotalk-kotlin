package eu.javeo.talk.kotlin.disjoint

abstract class Either<L, R> private constructor() {

	class Left<L>(val value: L) : Either<L, Nothing>() {
		override fun toString() = "Left($value)"
	}

	class Right<R>(val value: R) : Either<Nothing, R>() {
		override fun toString() = "Right($value)"
	}
}
