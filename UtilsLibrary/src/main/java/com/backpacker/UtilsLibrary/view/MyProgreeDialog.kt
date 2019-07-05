package com.backpacker.UtilsLibrary.view

import android.content.Context
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_view.*

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 17:15
 * @Purpose :菊花
 */
class MyProgreeDialog(context: Context) : AlertDialog(context, R.style.my_dialog) {
    private var animation: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_view)
        setCanceledOnTouchOutside(false)
        initView()
    }
    private fun initView() {
        animation = AnimationUtils.loadAnimation(context,
            R.anim.loading_animation)
//        dialog_image!!.startAnimation(animation)

    }
    override fun show() {
        super.show()
        dialog_image!!.startAnimation(animation)
    }
}