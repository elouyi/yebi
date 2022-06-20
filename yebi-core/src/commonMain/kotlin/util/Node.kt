package com.elouyi.yebi.util

import kotlin.jvm.JvmName

/**
 * Linked list nodes. Due to the immutability of [Cons], all modification operations return a new [Node].
 *
 * The end of nodes is always [Nil]
 * @see Nil
 * @see Cons
 */
public sealed interface Node<out T> {

    public object Nil : Node<Nothing>

    public class Cons<T>(
        public val head: T,
        public val tail: Node<T>
    ) : Node<T> {
        public companion object {
            public operator fun <T> invoke(node: Node<T>, tail: Node<T>): Node<T> = when(node) {
                Nil -> tail
                is Cons<T> -> Cons(node.head, tail)
            }
        }
    }
}

@get:JvmName("isNodeEmpty")
public val Node<*>.isEmpty: Boolean
    get() = this === Node.Nil

public fun <T> Node<T>.add(value: T): Node<T> = when(this) {
    Node.Nil -> Node.Cons(value, Node.Nil)
    is Node.Cons<T> -> Node.Cons(value, this)
}

public fun <T> Node<T>.add(other: Node<T>): Node<T> = when(other) {
    Node.Nil -> this
    is Node.Cons<T> -> Node.Cons(other.head, this)
}

public fun <T> Node<T>.remove(
    value: T
): Node<T> = when(this) {
    Node.Nil -> this
    is Node.Cons<T> -> {
        if (head == value) tail
        else Node.Cons(head, tail.remove(value))
    }
}

public fun <T> Node<T>.remove(
    other: Node<T>
): Node<T> = when(other) {
    Node.Nil -> this
    is Node.Cons<T> -> remove(other.head)
}

public tailrec fun <T> Node<T>.forEach(
    action: (T) -> Unit
): Unit = when(this) {
    Node.Nil -> Unit
    is Node.Cons<T> -> {
        action(head)
        tail.forEach(action)
    }
}

public inline fun <T> Node<T>.forEachWithIterator(
    action: (T) -> Unit
) { for (value in this) action(value) }

public inline fun <reified U : T, T> Node<T>.loopOn(
    crossinline action: (U) -> Unit
): Unit = forEach {
    if (it is U) action(it)
}

public inline fun <reified U : T, T> Node<T>.loopOnWithIterator(
    action: (U) -> Unit
): Unit = forEachWithIterator {
    if (it is U) action(it)
}

public operator fun <T> Node<T>.iterator(): Iterator<T> {
    return object : Iterator<T> {
        var cur: Node<T> = Node.Cons<T>(this@iterator, this@iterator)

        override fun hasNext(): Boolean {
            return cur is Node.Cons<T> && (cur as Node.Cons<T>).tail !is Node.Nil
        }

        override fun next(): T {
            val p = (cur as Node.Cons<T>)
            return (p.tail as Node.Cons<T>).head.also { cur = p.tail }
        }
    }
}

public fun <T> Node<T>.forEachCons(
    action: (Node.Cons<T>) -> Unit
): Unit = when(this) {
    Node.Nil -> Unit
    is Node.Cons<T> -> {
        action(this)
        tail.forEachCons(action)
    }
}

public fun <T> Node<T>.reverse(): Node<T> = when(this) {
    Node.Nil -> this
    is Node.Cons<T> -> {
        var res: Node<T> = Node.Nil
        forEach {
            res = Node.Cons(it, res)
        }
        res
    }
}

public fun <T> Node<T>.asSequence(): Sequence<T> = Sequence(::iterator).constrainOnce()

public fun <T> Node<T>.asIterable(): Iterable<T> = Iterable(::iterator)
