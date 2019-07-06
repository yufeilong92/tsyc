package com.example.tsyc.retrofit

import android.content.Context
import com.backpacker.UtilsLibrary.kotlin.NetWork
import com.backpacker.UtilsLibrary.kotlin.T
import com.backpacker.UtilsLibrary.net.CommonInterceptor
import com.example.tsyc.netserver.Request_Net
import com.example.tsyc.vo.DataMessageVo
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by apple on 2018/7/8.
 */
object RetrofitFactory {

    //    val BASE_URL: String = "http://zzzh.natapp1.cc/"
    val BASE_URL: String = DataMessageVo.mHttp

    private val TIMEOUT: Long = 60
    private var mainRetrofit: Request_Net? = null
    private var mineRetrofit: Request_Net? = null
    val interceptor = CommonInterceptor()
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    private fun buildGson(): Gson {
        return GsonBuilder().serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createMainRetrofit(): Request_Net {
        if (mainRetrofit == null) {
            mainRetrofit = createRetrofit().create(Request_Net::class.java)
        }
        return mainRetrofit!!
    }

    fun createMineRetrofit(): Request_Net {
        if (mineRetrofit == null) {
            mineRetrofit = createRetrofit().create(Request_Net::class.java)
        }
        return mineRetrofit!!
    }

    /**
     * 判断当前网络是否可用
     */
    fun judgmentNetWork(context: Context): Boolean {
        if (NetWork.isNetworkConnected(context)) {
            return true
        }
        T.showToast(context, "当前网络不可用")
        return false
    }


}