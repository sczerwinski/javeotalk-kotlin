package eu.javeo.talk.delegation.calc

object EratosthenesSieve {

	private var time: Long = 0 // TODO: Properly calculate time of each computation

	fun findPrimes(limit: Int): List<Int> {
		tailrec fun sieve(numbers: List<Int>, primes: List<Int>): List<Int> =
			if (numbers.isEmpty()) primes
			else sieve(numbers.filterNot { it % numbers.first() == 0 }, primes + numbers.first())

		println("Finding prime numbers up to $limit")
		time = 0L
		val primes = sieve(listOf(2..limit).flatten(), emptyList())
		println("Calculation finished in $time milliseconds")
		return primes
	}
}
