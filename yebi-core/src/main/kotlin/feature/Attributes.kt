package com.elouyi.yebi.feature

import com.elouyi.yebi.feature.internal.AttributesImpl

@JvmInline
public value class AttributeKey<T>(public val name: String) {
    override fun toString(): String = "AttributeKey: $name"
}

public fun Attributes(): Attributes = AttributesImpl()

public interface Attributes {

    public operator fun <T : Any> get(key: AttributeKey<T>): T =
        getOrNull(key) ?: throw IllegalStateException("key ${key.name} not found,yebi may need to install(${key.name})")

    public fun <T> getOrNull(key: AttributeKey<T>): T?

    public operator fun contains(key: AttributeKey<*>): Boolean

    public operator fun <T : Any> set(key: AttributeKey<T>, value: T): Unit = put(key, value)

    public  fun <T : Any> put(key: AttributeKey<T>, value: T)

    public fun <T : Any> remove(key: AttributeKey<T>)

    public fun <T : Any> take(key: AttributeKey<T>): T = get(key).also { remove(key) }

    public fun <T : Any> takeOrNull(key: AttributeKey<T>): T? = getOrNull(key).also { remove(key) }

    public fun <T : Any> computeIfAbsent(key: AttributeKey<T>, block: () -> T): T

    public val allKeys: List<AttributeKey<*>>
}

public fun Attributes.putAll(other: Attributes) {
    other.allKeys.forEach {
        @Suppress("UNCHECKED_CAST")
        put(it as AttributeKey<Any>, other[it])
    }
}
