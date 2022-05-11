package com.elouyi.yebi.feature.live

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.YebiFeature

public class Live internal constructor(internal val config: Config) {

    private lateinit var scope: YebiBot

    internal lateinit var liveAPI: LiveAPI

    public class Config(
        public var heartbeatInterval: Int = 10000
    )

    public companion object Feature : YebiFeature<Config, Live> {

        override val key: AttributeKey<Live> = AttributeKey("Live")

        override fun prepare(block: Config.() -> Unit): Live {
            val config = Config().apply(block)
            return Live(config)
        }

        override fun install(feature: Live, scope: YebiBot) {
            feature.scope = scope
            feature.liveAPI = LiveAPI(scope)
        }
    }
}
