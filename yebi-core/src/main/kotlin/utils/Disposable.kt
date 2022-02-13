package com.elouyi.yebi.utils

public interface Disposable {
    public fun dispose()
}

public sealed interface DisposableList {

    public object Nil : DisposableList

    public class Cons(
        public val head: Disposable,
        public val tail: DisposableList
    ) : DisposableList
}

public fun DisposableList.remove(
    disposableHandle: Disposable
): DisposableList = when(this) {
    DisposableList.Nil -> this
    is DisposableList.Cons -> {
        if (head == disposableHandle) tail
        else DisposableList.Cons(head, tail.remove(disposableHandle))
    }
}

public tailrec fun DisposableList.forEach(
    action: (Disposable) -> Unit
): Unit = when(this) {
    DisposableList.Nil -> Unit
    is DisposableList.Cons -> {
        action(head)
        tail.forEach(action)
    }
}

public inline fun <reified T : Disposable> DisposableList.loopOn(
    crossinline action: (T) -> Unit
): Unit = forEach {
    if (it is T) action(it)
}
