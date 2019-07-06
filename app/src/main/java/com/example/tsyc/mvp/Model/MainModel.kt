package com.example.tsyc.mvp.Model

import android.content.Context
import com.example.tsyc.mvp.Contrat.MainView
import com.example.tsyc.netserver.Request_Net
import com.zzzh.akhalteke.mvp.view.RequestResultInterface

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.mvp.Model
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:09
 * @Purpose :主界面数据层
 */
class MainModel : MainView.Model {
    override fun requestGson(context: Context, request: RequestResultInterface) {
        Request_Net.getString({
            request.Success(it)
        }, {
            request.onComplise()

        }, {
            request.onError(it)
        })
    }
}