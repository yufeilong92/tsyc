package com.backpacker.UtilsLibrary.kotlin

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:02
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object PhoneUtil {
    /**
     * 150*****7856
     *
     * @param phone
     * @return
     */
    fun phoneHineNumber(phone: String): String {
        val str = "****"
        val sb = StringBuilder(phone)
        sb.replace(3, 7, str)
        return sb.toString()
    }
    /**
     * 判断是否为手机号码
     *
     * @return
     */
    fun isPhoneNum(phone: String): Boolean {
        val pattern = Pattern.compile("1[0-9]{10}")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }


    /**
     * 判断是否400服务代码
     *
     * @param num
     * @return
     */
    fun is400or800(num: String): Boolean {
        return (!TextUtils.isEmpty(num)
                && (num.startsWith("400") || num.startsWith("800"))
                && num.length == 10)
    }

    /**
     * 判断是否区域号码
     *
     * @param num
     * @return
     */
    fun isAdCode(num: String): Boolean {
        return (!TextUtils.isEmpty(num) && num.matches("[0]{1}[0-9]{2,3}".toRegex())
                && !Util.isRepeatedStr(num))
    }

    /**
     * 判断是否座机号码
     *
     * @param num
     * @return
     */
    fun isPhoneHome(num: String): Boolean {
        return (!TextUtils.isEmpty(num) && num.matches("[0-9]{7,8}".toRegex())
                && !Util.isRepeatedStr(num))
    }
}