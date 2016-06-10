package eu.javeo.talk.kotlin

import eu.javeo.talk.kotlin.disjoint.Either
import org.junit.Test
import kotlin.test.expect

class EitherTest {

	@Test
	fun eitherLeft() {
		// TODO: Nothing -> String
		val x: Either<Exception, Nothing> = Either.Left(Exception())
		expect("") {
			when (x) {
				is Either.Left -> x.value.message ?: ""
				is Either.Right -> "right"
				else -> "something else"
			// TODO: get rid of else
			}
		}
	}

	@Test
	fun eitherRight() {
		// TODO: Nothing -> Exception
		val x: Either<Nothing, String> = Either.Right("Hello, World!")
		expect("Hello, World!") {
			when (x) {
				is Either.Left -> "left"
				is Either.Right -> x.value
				else -> "something else"
			// TODO: get rid of else
			}
		}
	}

	@Test
	fun mapEitherOfLists() {
		val x = Either.Right(listOf("1", "2", "3", "4", "5"))
		// TODO: x.mapRightItems { it.toInt() }
	}

	@Test
	fun partitionListOfEithers() {
		val list = listOf(Either.Right("1"), Either.Left(Exception()), Either.Right("2"), Either.Right("3"), Either.Left(Exception()), Either.Right("4"))
		val (lefts, rights) = list.partition { it is Either.Left }
		println(lefts)
		println(rights)
		// TODO: rights.map { it.value.toInt() }
	}

	@Test
	fun nullableEither() {
		val x: Either<Exception, String?>
		// TODO: Either<Exception, String?> -> handle null
	}
}
