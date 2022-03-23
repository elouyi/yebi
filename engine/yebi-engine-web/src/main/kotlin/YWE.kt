package com.elouyi.yebi.engine.web

import com.elouyi.yebi.engine.YebiEngine
import com.elouyi.yebi.engine.YebiEngineFactory
import com.elouyi.yebi.engine.web.internal.YebiWebEngine

public object YWE : YebiEngineFactory<WebEngineConfig> {
    override fun create(block: WebEngineConfig.() -> Unit): YebiEngine {
        return YebiWebEngine(WebEngineConfig().apply(block))
    }
}
