package com.example.tsyc.mvp.Contrat

import android.content.Context
import com.zzzh.akhalteke.mvp.view.RequestResultInterface

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.mvp.Contrat
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:08
 * @Purpose :主界面数据
 */
interface MainView {
    interface View {
        fun Success(t: Any?)
        fun Error(ex: Throwable)
        fun Complise()
    }

    interface Model {
        fun requestGson(context: Context, request: RequestResultInterface)
    }

    interface Presenter {
        fun initMvp(view: View, model: Model)
        fun requestGson(context: Context)
    }
}