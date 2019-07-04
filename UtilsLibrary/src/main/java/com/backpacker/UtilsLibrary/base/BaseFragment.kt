package com.backpacker.UtilsLibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:43
 * @Purpose :碎片基类
 */
abstract class BaseFragment: Fragment() {

    abstract fun setContentView(): Int
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setContentView(), container, false)
    }
    abstract fun setCreatedContentView(view: View, savedInstanceState: Bundle?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setCreatedContentView(view, savedInstanceState)
    }

}