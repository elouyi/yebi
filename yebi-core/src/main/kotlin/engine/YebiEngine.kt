package com.elouyi.yebi.engine

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.utils.YebiInternalAPI
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import mu.KLogger
import mu.KotlinLogging
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

public sealed interface YebiEngine : CoroutineScope, Closeable {

    public val uid: Long

    public val config: YebiEngineConfig

    public val client: HttpClient

    @YebiInternalAPI
    public fun install(bot: YebiBot)

    public val isLogin: Boolean

    public val logger: KLogger

    public fun HttpRequestBuilder.doSomething()

    public fun wrapURL(url: String, vararg params: Pair<String, String>): String

    override fun close()
}

public abstract class YebiEngineBase(private val engineName: String) : YebiEngine {

    override val coroutineContext: CoroutineContext = SupervisorJob() + CoroutineName(engineName)

    final override val logger: KLogger = KotlinLogging.logger(engineName)

    override fun wrapURL(url: String, vararg params: Pair<String, String>): String {
        error("URL $url is not supported in engine $engineName")
    }

    override fun toString(): String {
        return "YebiEngine: $engineName"
    }
}
