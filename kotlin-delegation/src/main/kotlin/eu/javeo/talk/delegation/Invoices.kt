package eu.javeo.talk.delegation

import eu.javeo.talk.delegation.model.finance.Invoice
import eu.javeo.talk.delegation.model.finance.InvoiceItem
import eu.javeo.talk.delegation.model.finance.Product

val pen = Product("Pen", 2.00)
val stickyTape = Product("Sticky tape", 5.00)
val ruler = Product("Ruler", 3.00)
val calculator = Product("Calculator", 20.00)

fun main(args: Array<String>) {
	val invoices = listOf(
			Invoice("001/2017", mutableListOf(
					InvoiceItem(pen, 2000))),
			Invoice("002/2017", mutableListOf(
					InvoiceItem(pen, 1),
					InvoiceItem(stickyTape, 2),
					InvoiceItem(ruler, 2))),
			Invoice("003/2017", mutableListOf(
					InvoiceItem(calculator, 1))),
			Invoice("004/2017", mutableListOf(
					InvoiceItem(pen, 2),
					InvoiceItem(stickyTape, 1),
					InvoiceItem(ruler, 1))))

	println("Total revenue: " + invoices.sumByDouble { it.total })
	println("Total pens sold: " + invoices.flatMap { it.items }.filter { it.product == pen }.sumBy { it.quantity })

	invoices.forEach {
		it.items.forEach {
			println("${it.product.name}, ${it.quantity} pcs. – ${it.value} PLN")
		}
	}
}
