package com.elouyi.yebi

import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.YebiFeature

public class YebiConfig {

    private val features: MutableMap<AttributeKey<*>, (Yebi) -> Unit> = mutableMapOf()
    private val featureConfigurations: MutableMap<AttributeKey<*>, Any.() -> Unit> = mutableMapOf()

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

    public fun install(yebi: Yebi) {
        features.values.forEach { yebi.apply(it) }
    }

    override fun toString(): String {
        return features.toString() + "\n" + featureConfigurations.toString()
    }
}
