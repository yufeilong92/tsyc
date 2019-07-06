package com.example.tsyc.application

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.Application
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 16:44
 * @Purpose :
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initbug()
    }

    fun initbug() {
        CrashReport.initCrashReport(applicationContext)
    }
}