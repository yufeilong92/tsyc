package com.backpacker.UtilsLibrary.kotlin

import java.lang.Exception

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary
 * @Description:    字符串工具类
 * @author: L-BackPacker
 * @date:   2019/3/31 21:14
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object StringUtil {

    fun isEmpty(str: String?): Boolean {
        if (str == null || str == "")
            return true
        return false
    }

}