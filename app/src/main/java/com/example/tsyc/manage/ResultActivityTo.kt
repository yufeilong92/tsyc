package com.example.tsyc.manage

import android.app.Activity
import com.backpacker.UtilsLibrary.base.StartActivityManger
import com.example.tsyc.SelectImagerActivity

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.manage
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:33
 * @Purpose :跳转管理
 */
open class ResultActivityTo(var context: Activity) : StartActivityManger(context) {
     fun toSelectCammer(){
         jumpTo(SelectImagerActivity::class.java)
     }
}