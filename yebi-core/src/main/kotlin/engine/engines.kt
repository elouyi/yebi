package com.elouyi.yebi.engine

import com.elouyi.yebi.engine.web.YWEngineConfig
import com.elouyi.yebi.engine.web.internal.YebiWebEngine

public object YWE : YebiEngineFactory<YWEngineConfig> {
    override fun create(block: YWEngineConfig.() -> Unit): YebiEngine {
        return YebiWebEngine(YWEngineConfig().apply(block))
    }
}