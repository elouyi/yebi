package com.elouyi.yebi.internal.engine

import com.elouyi.yebi.engine.YebiEngine
import com.elouyi.yebi.feature.user.UserFeature

internal suspend fun YebiEngine.login() {
    logger.info { "Bot start login..." }
    return try {
        val userFeature = botAttributes[UserFeature]
        userFeature.api.login().also {
            logger.info { "Login successful!" }
        }
    } catch (e: Throwable) {
        logger.error(e) { "Login failed." }
    }
}
