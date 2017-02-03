package eu.javeo.talk.delegation

import eu.javeo.talk.delegation.calc.EratosthenesSieve

fun main(args: Array<String>) {
	println(EratosthenesSieve.findPrimes(10000))
	println(EratosthenesSieve.findPrimes(20000))
	println(EratosthenesSieve.findPrimes(50000))
	println(EratosthenesSieve.findPrimes(100000))
	println(EratosthenesSieve.findPrimes(200000))
	println(EratosthenesSieve.findPrimes(500000))
}
