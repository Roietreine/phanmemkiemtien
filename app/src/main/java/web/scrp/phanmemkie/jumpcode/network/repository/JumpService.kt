package web.scrp.phanmemkie.jumpcode.network.repository

import web.scrp.phanmemkie.jumpcode.network.data.Request
import web.scrp.phanmemkie.jumpcode.network.data.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface JumpService {

    @POST("app_conf")
    suspend fun getJumpCode(@Body param : Request): Response
}