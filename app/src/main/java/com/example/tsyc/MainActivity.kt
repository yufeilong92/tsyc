package com.example.tsyc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.acker.simplezxing.activity.CaptureActivity
import com.example.tsyc.base.BaseActivity
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.example.tsyc.mvp.Contrat.MainView
import com.example.tsyc.mvp.Model.MainModel
import com.example.tsyc.mvp.Presenter.MainPresenter
import com.example.tsyc.updatenet.updateapp.UpdateAppHttpUtil
import com.example.tsyc.updatenet.util.CProgressDialogUtils.cancelProgressDialog
import com.example.tsyc.updatenet.util.CProgressDialogUtils.showProgressDialog
import com.vector.appupdatedemo.ext.toast
import com.vector.update_app.UpdateAppBean
import com.vector.update_app.utils.AppUpdateUtils
import com.vector.update_app_kotlin.check
import com.vector.update_app_kotlin.updateApp
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

/**
 * @Title:  主界面
 * @Package com.example.tsyc
 * @Description: 主界面
 * @author: L-BackPacker
 * @date:   2019/7/6 15:01
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019/7/6
 */

class MainActivity : BaseActivity() {

    override fun setInitContentView(): Int {
        return R.layout.activity_main
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
    }


}
