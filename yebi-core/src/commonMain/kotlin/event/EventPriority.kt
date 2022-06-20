package com.elouyi.yebi.event

// todo
public sealed class EventPriority(private val value: Int) : Comparable<EventPriority> {
    public object LOW : EventPriority(1)

    /**
     * default
     */
    public companion object NORMAL : EventPriority(2)

    public object HIGH : EventPriority(3)

    override fun compareTo(other: EventPriority): Int = this.value.compareTo(other.value)

    override fun toString(): String {
        return "EventPriority:${this::class.simpleName}"
    }

}
