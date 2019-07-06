package com.example.tsyc.manage

import androidx.fragment.app.FragmentActivity
import com.backpacker.UtilsLibrary.base.StartFragmentManger
import com.example.tsyc.SelectImagerActivity

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.manage
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:33
 * @Purpose :跳转管理
 */
open class ResultFragmentTo(var context: FragmentActivity) : StartFragmentManger(context) {
     fun toSelectCammer(){
         jumpTo(SelectImagerActivity::class.java)
     }
}