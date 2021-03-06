package com.elouyi.yebi

import com.elouyi.yebi.engine.YebiEngine
import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.engine.YebiEngineFactory
import com.elouyi.yebi.feature.Attributes
import com.elouyi.yebi.feature.user.installUserFeature
import com.elouyi.yebi.internal.engine.login

public fun <T : YebiEngineConfig> YebiBot(
    engineFactory: YebiEngineFactory<T>,
    block: YebiConfig<T>.() -> Unit = {}
): YebiBot {
    val config = YebiConfig<T>().apply(block.installUserFeature())
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

    public suspend fun login() {
        engine.login()
    }
}
