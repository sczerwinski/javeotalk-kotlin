package eu.javeo.talk.kotlin

import eu.javeo.talk.kotlin.list.Cons
import eu.javeo.talk.kotlin.list.Nil
import org.junit.Test
import java.util.NoSuchElementException
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.expect

class ListTest {

	@Test
	fun emptyList() {
		assertTrue { Nil<String>().isEmpty() }
		assertFailsWith(NoSuchElementException::class) {
			Nil<String>().head
		}
		assertFailsWith(NoSuchElementException::class) {
			Nil<String>().tail
		}
	}

	@Test
	fun singletonList() {
		fun <T> singleton(element: T) = Cons(element, Nil())

		assertFalse { singleton("text").isEmpty() }
		expect("text") { singleton("text").head }
		assertTrue { singleton("text").tail.isEmpty() }
	}
}
