package com.elouyi.yebi.feature.live.internal

/*
internal class LiveAPIImpl(private val scope: YebiBot) : LiveAPI() {

    @YebiExperimental
    override fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext,
        clazz: KClass<E>,
        block: suspend E.() -> Unit
    ): Cancelable {
        throw throw throw TODO("Not yet implemented")
    }

    override suspend fun danmuInfo(id: Int): BiliResponse<DanmuInfo> {
        val url = LiveUrl.liveDanmuInfo(id)
        return scope.engine.client.get(url) {
            with(scope.engine) { doSomething() }
        }
    }
}
*/
