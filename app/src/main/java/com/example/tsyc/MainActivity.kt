package com.example.tsyc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.acker.simplezxing.activity.CaptureActivity
import com.backpacker.UtilsLibrary.base.BaseActivity
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
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
        btn_two.setOnClickListener {
            if (Util.handleOnDoubleClick()) {
                return@setOnClickListener
            }
            PermissionUtils.showPermission(
                mContext,
                "",
                arrayOf(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
            ) {
                PictureSelector.create(mContext)
                    .openGallery(PictureMimeType.ofImage())
                    .forResult(PictureConfig.CHOOSE_REQUEST)
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
            PictureConfig.CHOOSE_REQUEST->{
                when(RESULT_OK){
                    // 图片、视频、音频选择结果回调
                        // 例如 LocalMedia 里面返回三种path
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                }

            }
        }
    }

}
