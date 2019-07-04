package com.backpacker.UtilsLibrary.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.backpacker.UtilsLibrary.kotlin.AppManager
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:26
 * @Purpose :activity基础类
 */
abstract class BaseActivity : Activity() {
    lateinit var mContext: BaseActivity
    val appManager: AppManager = AppManager.appManager

    companion object {
        val CNT_PARAMETE_TITLE: String = "param_title"
    }

    protected abstract fun setContentView(): Int;
    protected abstract fun onCreateView(savedInstanceState: Bundle?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentView())
        mContext = this
        appManager.addActivity(this)
        initTitle()
        onCreateView(savedInstanceState)
    }
    private fun initTitle() {
        if (intent != null) {
            val title = intent.getStringExtra(CNT_PARAMETE_TITLE)
            if (title != null) {
                setTitle(title)
            }
        }
    }
    fun test() {
        PermissionUtils.showPermission(mContext, "", arrayOf("")) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appManager.finishActivity(this)
    }
}