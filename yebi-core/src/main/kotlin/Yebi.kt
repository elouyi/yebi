package com.elouyi.yebi

import com.elouyi.yebi.feature.Attributes
import com.elouyi.yebi.utils.newClient
import io.ktor.client.*

public fun Yebi(
    block: YebiConfig.() -> Unit = {}
): Yebi {
    val config = YebiConfig().apply(block)
    return Yebi(newClient(), config)
}

public class Yebi(
    public val client: HttpClient,
    internal val config: YebiConfig = YebiConfig()
) {

    public val attributes: Attributes = Attributes()


    init {
        config.install(this)
    }

    override fun toString(): String {
        return "config: $config"
    }
}
