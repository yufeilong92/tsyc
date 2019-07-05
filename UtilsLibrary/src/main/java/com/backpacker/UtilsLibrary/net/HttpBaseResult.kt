package com.backpacker.UtilsLibrary.net

import android.content.Context
import com.backpacker.UtilsLibrary.kotlin.T
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.net
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 17:25
 * @Purpose :
 */
object HttpBaseResult {
    fun onError(mContext:Context,ex:Throwable){
        try {
            val error = if (ex is SocketTimeoutException) {
                "网络连接超时，请稍后再试..."
            } else if (ex is ConnectException) {
                "网络连接超时，请稍后再试..."
            } else if (ex is UnknownHostException) {
                "网络连接超时，请稍后再试..."
            } else {
                if (ex is ResultException) {
                    when (ex.status) {

                    }
                    (ex as ResultException).msg   //抛出异常，抓取数据
                } else {
                    "网络连接异常，请稍候重试"
                }
            }
            T.showToast(mContext, error)
            ex.printStackTrace()
        } catch (e1: IOException) {
            e1.printStackTrace()
        } finally {

        }
    }
    fun onComplate(){

    }

}