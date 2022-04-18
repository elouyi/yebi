package com.elouyi.yebi.feature

import com.elouyi.yebi.YebiBot
import io.ktor.client.*

public interface YebiAPIBase {
    public val bot: YebiBot

    public val client: HttpClient
}
