package eu.javeo.talk.delegation.model.finance

class Invoice(number: String, val items: List<InvoiceItem>) : Document(number) { // TODO: Make Invoice implement List<InvoiceItem>
	val total: Double = items.sumByDouble { it.value } // TODO: Lazy initialization
}
