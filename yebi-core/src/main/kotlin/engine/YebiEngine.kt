package com.elouyi.yebi.engine

import com.elouyi.yebi.YebiBot
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import java.io.Closeable

public interface YebiEngine : CoroutineScope, Closeable {

    public val uid: Long

    public val config: YebiEngineConfig

    public fun install(bot: YebiBot)

    public val isLogin: Boolean

    public fun HttpRequestBuilder.doSomething()
}

public abstract class YebiEngineBase(private val engineName: String) : YebiEngine {

}
