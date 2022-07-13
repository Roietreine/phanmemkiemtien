package web.scrp.phanmemkie.jumpcode.network.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import web.scrp.phanmemkie.jumpcode.network.data.Request
import web.scrp.phanmemkie.jumpcode.network.data.Response
import web.scrp.phanmemkie.jumpcode.network.di.RetrofitHelper
import web.scrp.phanmemkie.jumpcode.network.repository.JumpService

class JumpServiceImpl {

    private val service : JumpService = RetrofitHelper.service()

    suspend fun getJumpCodeUrl(param : Request): Flow<Response> = callbackFlow {
        trySend(service.getJumpCode(param))
        awaitClose()
    }
}