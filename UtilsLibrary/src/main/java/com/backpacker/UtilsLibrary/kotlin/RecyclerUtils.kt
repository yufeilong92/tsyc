package com.backpacker.UtilsLibrary.kotlin

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:46
 * @Purpose :列表工具类
 */
 abstract class RecyclerUtils(var mContext:Context) {
    /**
     * @METHOD 设置竖向管理器
     * @param rlv
     */
    @SuppressLint("WrongConstant")
    open fun setMangager(rlv: RecyclerView) {
        var layout = GridLayoutManager(mContext, 1)
        layout.orientation=GridLayoutManager.VERTICAL
        rlv.layoutManager = layout
    }

    /**
     * 设置横向管理器
     */
    open fun setHorizontalMangager(rlv: RecyclerView) {
        var layout = GridLayoutManager(mContext, 1)
        layout.orientation = GridLayoutManager.HORIZONTAL
        rlv.layoutManager = layout
    }

    /**
     * 设置竖向管理器
     */
    open fun setMangager(rlv: RecyclerView, number: Int, orientai: Int) {
        var layout = GridLayoutManager(mContext, number)
        layout.orientation = orientai
        rlv.layoutManager = layout
    }
}