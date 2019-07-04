package com.backpacker.UtilsLibrary.kotlin

import com.google.gson.Gson

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:05
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object GosnUtil {
    fun <T> getGosnT(success: String, c: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(success, c)
    }
}