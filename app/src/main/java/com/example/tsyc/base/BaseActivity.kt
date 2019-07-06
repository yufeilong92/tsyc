package com.example.tsyc.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.backpacker.UtilsLibrary.base.StartActivityManger
import com.backpacker.UtilsLibrary.kotlin.AppManager
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.backpacker.UtilsLibrary.net.HttpBaseResult
import com.backpacker.UtilsLibrary.view.MyProgreeDialog
import com.example.tsyc.manage.ResultActivityTo

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
    lateinit var progressDialog: MyProgreeDialog
    protected abstract fun setInitContentView(): Int;
    protected abstract fun onInitCreateView(savedInstanceState: Bundle?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setInitContentView())
        mContext = this
        progressDialog = MyProgreeDialog(this)
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

    fun showProgress() {
        Util.showDialog(progressDialog)
    }

    fun dismissProgress() {
        Util.dismissDialog(progressDialog)
    }

    fun onError(ex: Throwable) {
        dismissProgress()
        HttpBaseResult.onError(mContext, ex)
    }

    fun onComplate() {
        dismissProgress()
        HttpBaseResult.onComplate()
    }
}