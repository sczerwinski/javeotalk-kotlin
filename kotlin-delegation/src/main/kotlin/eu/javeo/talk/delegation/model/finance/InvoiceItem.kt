package eu.javeo.talk.delegation.model.finance

data class InvoiceItem(val product: Product, val quantity: Int) {
	val value: Double = product.price * quantity // TODO: Lazy initialization
}
