package com.example.tsyc.NetServer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.NetServer
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 15:52
 * @Purpose :接口
 */
interface Get_Interface {
    @GET("query")
    fun getBitmap(@Query("value") type: String, @Query("postid") postid: String): Call<String>;


}