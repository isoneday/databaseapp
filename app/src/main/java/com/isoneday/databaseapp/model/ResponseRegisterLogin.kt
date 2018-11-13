package com.isoneday.databaseapp.model


import com.google.gson.annotations.SerializedName


data class ResponseRegisterLogin(

    @field:SerializedName("result")
    val result: Int? = null,

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("username")
    val un: String? = null
)