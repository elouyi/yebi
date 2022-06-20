package com.elouyi.yebi.engine

public interface YebiEngineFactory<out Config : YebiEngineConfig> {
    public fun create(block: Config.() -> Unit = {}): YebiEngine
}
