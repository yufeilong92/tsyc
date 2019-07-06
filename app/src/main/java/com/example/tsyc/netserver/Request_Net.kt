package com.example.tsyc.netserver

import android.util.Log
import com.backpacker.UtilsLibrary.net.BaseServiceUtil
import com.backpacker.UtilsLibrary.net.CommonInterceptor
import com.example.tsyc.vo.DataMessageVo
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.NetServer
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 15:56
 * @Purpose :请求综合
 */
object Request_Net {
    private val DEFAULT_TIMEOUT: Long = 10
    private val TAG: String = "[" + Request_Net::class.java.simpleName + "]="
    fun getBitmap(success: (t: String) -> Unit, complete: () -> Unit, error: (str: String) -> Unit) {
        val observer = object : Observer<String> {
            override fun onComplete() {
                Log.i(TAG, "onComplete")
                complete()
            }

            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i(TAG, "onNext")
                success(t)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError" + e.message)
                error(e.message!!)
            }

        }
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                val interceptor = CommonInterceptor()
                val clienBuilde = OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

                var retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(DataMessageVo.mImager);
                clienBuilde.interceptors().clear()
                clienBuilde.interceptors().add(interceptor)
                val build = clienBuilde.build()
                val client = retrofit.client(build).build()
                val create = client.create(Get_Interface::class.java)
                val bitmap = create.getBitmap("shentong", "3708293428085")
                bitmap.enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        emitter.onError(t)
                        emitter.onComplete()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            val body = response.body()!!.toString()
                            emitter.onNext(body)
                            emitter.onComplete()

                        } else {
                            var t = Throwable(response.message().toString())
                            emitter.onError(t)
                            emitter.onComplete()
                        }

                    }

                })

            }

        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(observer)

    }

    fun getGson(success: (t: Any) -> Unit, complete: () -> Unit, error: (str: Throwable) -> Unit) {
        val createService = BaseServiceUtil.createService(Get_Interface::class.java, DataMessageVo.mHttp)
        val gosn = createService.getGosn()

    }

    fun getString(success: (t: Any) -> Unit, complete: () -> Unit, error: (str: Throwable) -> Unit) {
        val service = BaseServiceUtil.createService(Get_Interface::class.java, DataMessageVo.mHttp)
        val call = service.getString()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                error(t)
                complete()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    success(response.body().toString())
                } else {
                    error(Throwable(response.message()))
                }
                complete()
            }

        })
    }


}
