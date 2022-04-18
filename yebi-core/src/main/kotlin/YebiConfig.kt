package com.elouyi.yebi

import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.YebiFeature
import com.elouyi.yebi.utils.YebiInternalAPI

public class YebiConfig<T : YebiEngineConfig> {

    private val features: MutableMap<AttributeKey<*>, (YebiBot) -> Unit> = mutableMapOf()
    private val featureConfigurations: MutableMap<AttributeKey<*>, Any.() -> Unit> = mutableMapOf()

    internal var engineConfig: T.() -> Unit = {}

    public fun engine(block: T.() -> Unit) {
        val old = engineConfig
        engineConfig = {
            old()
            block()
        }
    }

    public fun <TBuilder : Any, TFeature : Any> install(
        feature: YebiFeature<TBuilder, TFeature>,
        configure: TBuilder.() -> Unit = {}
    ) {
        val preConfigBlock = featureConfigurations[feature.key]
        featureConfigurations[feature.key] = {
            preConfigBlock?.invoke(this)

            @Suppress("UNCHECKED_CAST")
            (this as TBuilder).configure()
        }
        if (features.containsKey(feature.key)) return
        features[feature.key] = {
            val attributes = it.attributes
            val config = it.config.featureConfigurations[feature.key]!!
            val featureData = feature.prepare(config)
            feature.install(featureData, it)
            attributes[feature.key] = featureData
        }
    }

    @YebiInternalAPI
    public fun install(yebiBot: YebiBot) {
        features.values.forEach { yebiBot.apply(it) }
    }

    override fun toString(): String {
        return features.toString() + "\n" + featureConfigurations.toString()
    }
}
