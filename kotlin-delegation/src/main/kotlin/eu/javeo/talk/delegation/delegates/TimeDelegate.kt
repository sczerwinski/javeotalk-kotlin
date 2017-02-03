package eu.javeo.talk.delegation.delegates

import java.util.*
import kotlin.reflect.KProperty

class TimeDelegate(initialValue: Long = 0L) { // TODO: Implement

	private fun currentTimeStamp(): Long = Date().time

	operator fun getValue(thisRef: Any?, property: KProperty<*>): Long =
			throw UnsupportedOperationException()

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
		throw UnsupportedOperationException()
	}
}
