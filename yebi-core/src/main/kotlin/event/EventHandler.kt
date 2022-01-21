package com.elouyi.yebi.event

public typealias EventHandler<R, T> = suspend R.(T) -> Unit
