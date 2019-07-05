package com.example.tsyc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.acker.simplezxing.activity.CaptureActivity
import com.example.tsyc.Base.BaseActivity
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.example.tsyc.Mvp.Contrat.MainView
import com.example.tsyc.Mvp.Model.MainModel
import com.example.tsyc.Mvp.Presenter.MainPresenter
import com.example.tsyc.UpDateNet.updateapp.UpdateAppHttpUtil
import com.example.tsyc.UpDateNet.util.CProgressDialogUtils.cancelProgressDialog
import com.example.tsyc.UpDateNet.util.CProgressDialogUtils.showProgressDialog
import com.google.gson.Gson
import com.vector.appupdatedemo.ext.toast
import com.vector.update_app.UpdateAppBean
import com.vector.update_app.utils.AppUpdateUtils
import com.vector.update_app_kotlin.check
import com.vector.update_app_kotlin.updateApp
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : BaseActivity(), MainView.View {

    override fun setInitContentView(): Int {
        return R.layout.activity_main
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
        initView()
        initRequest()
    }

    private var mPresenter: MainPresenter? = null
    fun initRequest() {
        mPresenter = MainPresenter()
        mPresenter!!.initMvp(this, MainModel())
    }

    fun initView() {
        btn_one.setOnClickListener {
            if (Util.handleOnDoubleClick()) {
                return@setOnClickListener
            }
            PermissionUtils.showPermission(mContext, "", arrayOf(Permission.CAMERA)) {
                startCaptureActivityForResult()
            }
        }
        btn_two.setOnClickListener {
            if (Util.handleOnDoubleClick()) {
                return@setOnClickListener
            }
            mResultTo.toSelectCammer()
        }
        btn_three.setOnClickListener {
            PermissionUtils.showPermission(
                mContext, "",
                arrayOf(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
            ) {
                upDateApp()
            }
        }
        btn_four.setOnClickListener {
            showProgress()
            mPresenter!!.requestGson(this)
        }
    }


    private fun startCaptureActivityForResult() {
        val intent = Intent(this@MainActivity, CaptureActivity::class.java)
        val bundle = Bundle()
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP)
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION)
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE)
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF)
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO)
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN)
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT)
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle)
        startActivityForResult(intent, CaptureActivity.REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CaptureActivity.REQ_CODE -> {
                when (resultCode) {
                    RESULT_OK -> {
                        Log.e("二维码扫描结果=", data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT) + "");
                        Toast.makeText(
                            mContext,
                            "${data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT)}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    RESULT_CANCELED -> {
                        Toast.makeText(
                            mContext,
                            "请求失败${data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT)}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("二维码扫描结果=", data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT) + "");
                    }
                }

            }

        }
    }

    fun upDateApp() {
//        val mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt"
        val mUpdateUrl = "http://zzzh56.com:8082/androidDriverVersion.json"
        //下载路径
        val path = mContext.externalCacheDir!!.absolutePath + File.separator
        //自定义参数
        val params = HashMap<String, String>()
        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
        params.put("appVersion", AppUpdateUtils.getVersionName(this))
        params.put("key1", "value2")
        params.put("key2", "value3")
        updateApp(mUpdateUrl, UpdateAppHttpUtil())
        //自定义配置
        {
            //以下设置，都是可选
            //设置请求方式，默认get
            isPost = false
            //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
//            setParams(params)
            //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
            hideDialogOnDownloading()
            //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
            topPic = R.mipmap.top_8
            //为按钮，进度条设置颜色。
            themeColor = 0xffffac5d.toInt()
//            themeColor = 0xf1e4655d.toInt()
            //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
            targetPath = path
            //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//            setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")

        }.check {
            onBefore {
                showProgressDialog(this@MainActivity)
            }
            //自定义解析
            parseJson {
                var isUpdate = false
                var isUpdataApp = ""
//                    val jsonObject = JSONObject(it)
                UpdateAppBean()
                    //（必须）是否更新Yes,No
                    .setUpdate("Yes")
                    //（必须）新版本号，
                    .setNewVersion("1.2")
                    //（必须）下载地址
                    .setApkFileUrl("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk")
                    //（必须）更新内容
                    .setUpdateLog("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk")
                    //大小，不设置不显示大小，可以不设置
                    .setTargetSize("1.2")
                    //是否强制更新，可以不设置
                    .setConstraint(false)
            }
            noNewApp {
                toast("当前已经是最新版本")
            }
            onAfter {
                cancelProgressDialog(this@MainActivity)
            }
        }
    }

    override fun Success(t: Any?) {

    }

    override fun Error(ex: Throwable) {
        this.onError(ex)

    }

    override fun Complise() {
        this.onComplate()
    }


}
