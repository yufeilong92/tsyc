package com.example.tsyc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.acker.simplezxing.activity.CaptureActivity
import com.backpacker.UtilsLibrary.base.BaseActivity
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val REQ_CODE_PERMISSION = 0x1111
    override fun setContentView(): Int {
        return R.layout.activity_main
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        initView()
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
                        Toast.makeText(mContext,"${data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT)}",Toast.LENGTH_SHORT).show()
                    }
                    RESULT_CANCELED -> {
                        Toast.makeText(mContext,"请求失败${data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT)}",Toast.LENGTH_SHORT).show()
                        Log.e("二维码扫描结果=", data!!.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT) + "");
                    }
                }

            }
        }
    }

}
