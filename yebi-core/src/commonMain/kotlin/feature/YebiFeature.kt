package com.elouyi.yebi.feature

import com.elouyi.yebi.YebiBot

public interface YebiFeature<out TConfig : Any, TFeature : Any> {
    public val key: AttributeKey<TFeature>

    public fun prepare(block: TConfig.() -> Unit = {}): TFeature

    public fun install(feature: TFeature, scope: YebiBot)

}