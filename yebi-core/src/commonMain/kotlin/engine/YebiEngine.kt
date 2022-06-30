package com.elouyi.yebi.engine

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.feature.Attributes
import com.elouyi.yebi.platformUtil
import com.elouyi.yebi.util.YebiInternalAPI
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import mu.KLogger
import mu.KotlinLogging
import kotlin.coroutines.CoroutineContext

public interface YebiEngine : CoroutineScope, Closeable {

    public val engineName: String

    public val config: YebiEngineConfig

    public val client: HttpClient

    public val logger: KLogger

    public val botAttributes: Attributes

    @YebiInternalAPI
    public fun install(bot: YebiBot)
}

public abstract class AbstractYebiEngine(
    final override val engineName: String
) : YebiEngine {

    override val coroutineContext: CoroutineContext = SupervisorJob() + CoroutineName(engineName)

    override val client: HttpClient = platformUtil.newHttpClient {
        BrowserUserAgent()
    }

    final override val logger: KLogger = KotlinLogging.logger(engineName)

    override fun install(bot: YebiBot) {

    }

    override fun toString(): String {
        return "YebiEngine: $engineName"
    }
}