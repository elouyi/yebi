@file:Suppress("UNCHECKED_CAST")

package com.elouyi.yebi.feature.internal

import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.Attributes

internal class AttributesImpl : Attributes {
    private val delegate: MutableMap<AttributeKey<*>, Any?> = mutableMapOf()

    override fun <T> getOrNull(key: AttributeKey<T>): T? = delegate[key] as T?

    override fun contains(key: AttributeKey<*>): Boolean = key in delegate

    override fun <T : Any> put(key: AttributeKey<T>, value: T) {
        delegate[key] = value
    }

    override fun <T : Any> remove(key: AttributeKey<T>) {
        delegate.remove(key)
    }

    override val allKeys: List<AttributeKey<*>>
        get() = delegate.keys.toList()

    override fun <T : Any> computeIfAbsent(key: AttributeKey<T>, block: () -> T): T {
        delegate[key]?.let { return it as T }
        val result = block()
        return (delegate.put(key, result) ?: result) as T
    }
}
