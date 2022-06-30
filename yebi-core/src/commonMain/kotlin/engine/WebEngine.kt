package com.elouyi.yebi.engine

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.feature.Attributes
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

public class WebEngineConfig : YebiEngineConfig {

}

internal class WebEngine(
    override val config: WebEngineConfig
) : AbstractYebiEngine("WebEngine") {

    private lateinit var bot: YebiBot

    override val coroutineContext: CoroutineContext = super.coroutineContext

    override val botAttributes: Attributes
        get() = bot.attributes

    override fun install(bot: YebiBot) {
        logger.trace { "install bot" }
        this.bot = bot
        super.install(bot)
    }

    override fun close() {
        logger.info { "engine close" }
        coroutineContext[Job]?.cancel()
    }
}

public object Web : YebiEngineFactory<WebEngineConfig> {

    override fun create(block: WebEngineConfig.() -> Unit): YebiEngine {
        val config = WebEngineConfig().also(block)
        return WebEngine(config)
    }
}
