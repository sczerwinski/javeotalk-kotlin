package eu.javeo.talk.kotlin

import org.junit.Test

class EitherTest {

	@Test
	fun mapEitherOfLists() {
		// TODO: either<Exception, List<String>>.mapRightItems { it.toInt() }
	}

	@Test
	fun partitionListOfEithers() {
		// TODO: List<Either<Exception, String>> -> Pair(List<Left<Exception>>, List<Right<String>>)
	}

	@Test
	fun nullableEither() {
		// TODO: Either<Exception, String?> -> handle null
	}
}
