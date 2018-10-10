package com.shanan.schedulist.networking

import com.shanan.schedulist.model.AuthResponse
import com.shanan.schedulist.model.CitiesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface LufthansaApi {

    @FormUrlEncoded
    @POST("oauth/token")
    fun authenticate(@Field("client_id") clientId: String,
                     @Field("client_secret") clientSecret: String,
                     @Field("grant_type") grantType: String):
            Observable<AuthResponse>

    @GET("references/cities")
    fun getCities(@Query("limit") limit: Int,
                  @Query("offset") offset: Int):
            Observable<CitiesResponse>
}