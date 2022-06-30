package com.elouyi.yebi.feature.user

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.YebiConfig
import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.YebiFeature
import com.elouyi.yebi.feature.internal.UserAPIImpl

@PublishedApi
internal fun <T : YebiEngineConfig> (YebiConfig<T>.() -> Unit).installUserFeature(): YebiConfig<T>.() -> Unit = {
    this@installUserFeature()
    install(UserFeature)
}

public class UserFeature internal constructor(
    internal val config: Config
) {

    public class Config

    private lateinit var bot: YebiBot

    internal lateinit var api: UserAPI

    /**
     * [UserFeature] 默认被 [YebiConfig.install] 至 [YebiBot], 可重复 install 覆盖 [Config]
     */
    public companion object Feature : YebiFeature<Config, UserFeature> {

        override val key: AttributeKey<UserFeature> = AttributeKey("UserFeature")

        override fun prepare(block: Config.() -> Unit): UserFeature {
            val config = Config().also(block)
            return UserFeature(config)
        }

        override fun install(feature: UserFeature, scope: YebiBot) {
            feature.bot = scope
            feature.api = UserAPIImpl(scope)
        }
    }
}
