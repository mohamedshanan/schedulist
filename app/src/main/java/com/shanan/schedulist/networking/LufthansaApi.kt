package com.shanan.schedulist.networking

import com.shanan.schedulist.model.AirportsResponse
import com.shanan.schedulist.model.AuthResponse
import com.shanan.schedulist.utils.Constants.AUTHORIZATION_HEADER
import io.reactivex.Observable
import retrofit2.http.*

interface LufthansaApi {

    @FormUrlEncoded
    @POST("oauth/token")
    fun authenticate(@Field("client_id") clientId: String,
                     @Field("client_secret") clientSecret: String,
                     @Field("grant_type") grantType: String):
            Observable<AuthResponse>

    @GET("references/airports")
    fun getAllAirports(@Header(AUTHORIZATION_HEADER) token: String):
            Observable<AirportsResponse>
}