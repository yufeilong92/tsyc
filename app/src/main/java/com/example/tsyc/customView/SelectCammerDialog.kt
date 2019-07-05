package com.example.tsyc.customView

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AlertDialog
import com.example.tsyc.R
import kotlinx.android.synthetic.main.cammer_dialog.*
import me.nereo.multi_image_selector.MultiImageSelector

/**
 * @Title:  tsyc
 * @Package com.example.tsyc.customView
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/7/5 9:10
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
 class SelectCammerDialog(var mContext: Context) : AlertDialog(mContext,R.style.myDialog) {
    private var metircs: DisplayMetrics = mContext.resources.displayMetrics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cammer_dialog)
        ininOnClick()
    }
    fun ininOnClick(){
        dialog_play_cammer.setOnClickListener {
        }
        dialog_from_phone.setOnClickListener {

        }


    }
}