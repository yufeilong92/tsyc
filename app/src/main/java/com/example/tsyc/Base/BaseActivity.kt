package com.example.tsyc.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.backpacker.UtilsLibrary.base.StartActivityManger
import com.backpacker.UtilsLibrary.kotlin.AppManager
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.example.tsyc.Manage.ResultActivityTo

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:26
 * @Purpose :activity基础类
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext: BaseActivity
    lateinit var mResultTo: ResultActivityTo
    val appManager: AppManager = AppManager.appManager

    protected abstract fun setInitContentView(): Int;
    protected abstract fun onInitCreateView(savedInstanceState: Bundle?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setInitContentView())
        mContext = this
        mResultTo = ResultActivityTo(this@BaseActivity)
        appManager.addActivity(this)
        initTitle()
        onInitCreateView(savedInstanceState)
    }

    private fun initTitle() {
        if (intent != null) {
            val title = intent.getStringExtra(StartActivityManger.CNT_PARAMETE_TITLE)
            if (title != null) {
                setTitle(title)
            }
        }
    }

    private fun test() {
        PermissionUtils.showPermission(mContext, "", arrayOf("")) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appManager.finishActivity(this)
    }
}