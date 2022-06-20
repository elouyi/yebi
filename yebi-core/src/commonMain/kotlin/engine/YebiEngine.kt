package com.elouyi.yebi.engine

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.util.YebiInternalAPI
import io.ktor.client.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import mu.KLogger

public interface YebiEngine : CoroutineScope, Closeable {
    public val config: YebiEngineConfig

    public val client: HttpClient

    public val logger: KLogger

    @YebiInternalAPI
    public fun install(bot: YebiBot)
}