package com.elouyi.yebi.utils

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
public inline fun newClient(
    ignoreUnknownKeys: Boolean = true,
    explicitNulls: Boolean = false,
    crossinline block: HttpClientConfig<CIOEngineConfig>.() -> Unit = {}
): HttpClient {
    val json = kotlinx.serialization.json.Json {
        this.ignoreUnknownKeys = ignoreUnknownKeys
        this.explicitNulls = explicitNulls
        //
    }

    return HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        block()
    }
}
