package com.example.tsyc.mvp.Presenter

import android.content.Context
import com.example.tsyc.mvp.Contrat.MainView
import com.zzzh.akhalteke.mvp.view.RequestResultInterface

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.mvp
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:06
 * @Purpose :主界面数据
 */
class MainPresenter :MainView.Presenter{
    var view: MainView.View? = null
    var model: MainView.Model? = null
    override fun initMvp(view: MainView.View, model: MainView.Model) {
        this.view = view
        this.model = model
    }
    override fun requestGson(context: Context) {
        model!!.requestGson(context,object :RequestResultInterface{
            override fun onError(ex: Throwable) {
                view!!.Error(ex)
            }
            override fun onComplise() {
                view!!.Complise()
            }
            override fun <T>Success(t: T){
                view!!.Success(t)
            }
        })
    }
}