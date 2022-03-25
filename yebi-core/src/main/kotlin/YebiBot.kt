package com.elouyi.yebi

import com.elouyi.yebi.engine.YebiEngine
import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.engine.YebiEngineFactory
import com.elouyi.yebi.feature.Attributes

/**
 * 构造一个 [YebiBot] 实例
 *
 * example:
 * ```kotlin
 * val yebi = Yebi(YWE) {  // 需要 yebi-engine-web 依赖
 *     // 通用配置
 *     install(Live) {  // 需要 yebi-live 依赖
 *
 *     }
 *     engine {
 *         // 具体 engine 的配置
 *     }
 * }
 * ```
 * @param engineFactory 引擎工厂，通常是一个 Object
 * @param block 配置
 */
public fun <T : YebiEngineConfig> YebiBot(
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

    override fun toString(): String {
        return "config: $config"
    }
}
