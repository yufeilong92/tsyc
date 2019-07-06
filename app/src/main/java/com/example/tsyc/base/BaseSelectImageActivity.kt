package com.example.tsyc.base

import android.net.Uri
import android.os.Bundle
import com.backpacker.UtilsLibrary.kotlin.PermissionUtils
import com.backpacker.UtilsLibrary.kotlin.TakePhotoUtils
import com.backpacker.UtilsLibrary.kotlin.Util
import com.backpacker.UtilsLibrary.view.SelectCammerDialog
import com.yanzhenjie.permission.Permission
import me.nereo.multi_image_selector.MultiImageSelector
import java.io.IOException

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:01
 * @Purpose :图片选择基类
 */
abstract class BaseSelectImageActivity : BaseActivity() {
    private val REQUEST_IMAGE_BACK = 1002//从相册选择
    private val PHOTO_PIC_CODE = 1001// 拍照
    lateinit var selectImageDialog: SelectCammerDialog
    private var temp = 1
    val imagePaths: ArrayList<String> = arrayListOf()
    override fun onInitCreateView(savedInstanceState: Bundle?) {
        selectImageDialog = object : SelectCammerDialog(mContext) {
            override fun onFromPhoto() {
                PermissionUtils.showPermission(
                    this@BaseSelectImageActivity, "需要照相和读写权限，是否同意", arrayOf(
                        Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    toSelectPhoto()
                }
            }

            override fun onTakePrice() {
                PermissionUtils.showPermission(
                    this@BaseSelectImageActivity, "需要照相和读写权限，是否同意", arrayOf(
                        Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    toPicture()
                }
            }

        }
    }

    private var takePhoneUri: Uri? = null
    private fun toPicture() {
        try {
            takePhoneUri = TakePhotoUtils.takePhoto(mContext, PHOTO_PIC_CODE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun toSelectPhoto() {
        if (temp == 1) {
            MultiImageSelector.create().showCamera(false).single().start(this, REQUEST_IMAGE_BACK)
        } else {
            MultiImageSelector.create().showCamera(false).count(temp).multi().origin(imagePaths)
                .start(this, REQUEST_IMAGE_BACK)
        }
    }

    fun toShowDialog() {
        Util.showDialog(selectImageDialog)
    }

    fun toShowDialog(num: Int) {
        temp = num
        Util.showDialog(selectImageDialog)
    }
}