package com.elouyi.yebi

import com.elouyi.yebi.engine.YebiEngine
import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.engine.YebiEngineFactory
import com.elouyi.yebi.feature.Attributes

public inline fun <T : YebiEngineConfig> YebiBot(
    engineFactory: YebiEngineFactory<T>,
    block: YebiConfig<T>.() -> Unit = {}
): YebiBot {
    val config = YebiConfig<T>().apply(block)
    return YebiBot(engineFactory.create(), config)
}

public class YebiBot(
    public val engine: YebiEngine,
    internal val config: YebiConfig<out YebiEngineConfig> = YebiConfig()
) {
    public val attributes: Attributes = Attributes()

    init {
        config.install(this)
        engine.install(this)
    }
}