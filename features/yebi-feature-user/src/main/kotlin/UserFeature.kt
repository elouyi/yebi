package com.elouyi.yebi.feature.user

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.feature.AttributeKey
import com.elouyi.yebi.feature.YebiFeature
import com.elouyi.yebi.feature.user.internal.UserAPIImpl

public class UserFeature internal constructor(internal val config: Config){

    private lateinit var scope: YebiBot

    internal lateinit var userAPI: UserAPI

    public class Config {

    }

    public companion object Feature : YebiFeature<Config, UserFeature> {
        override val key: AttributeKey<UserFeature> = AttributeKey("UserFeature")

        override fun prepare(block: Config.() -> Unit): UserFeature {
            val config = Config().apply(block)
            return UserFeature(config)
        }

        override fun install(feature: UserFeature, scope: YebiBot) {
            feature.scope = scope
            feature.userAPI = UserAPIImpl(scope)
        }
    }
}