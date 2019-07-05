package com.backpacker.UtilsLibrary.net

import java.io.IOException

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.net
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 17:26
 * @Purpose :
 */
class ResultException (
    var msg: String = "",
    var status: String = "",
    var data: String = "") : IOException() {

}