package com.isoneday.databaseapp.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestApi {

    @FormUrlEncoded
    @POST("register.php")
    fun registeruser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("level") level: String
    )

    @FormUrlEncoded
    @POST("login.php")
    fun loginuser(
        @Field("username") username: String,
        @Field("password") password: String
    )


}
