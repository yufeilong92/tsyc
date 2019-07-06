package com.example.tsyc.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tsyc.manage.ResultFragmentTo

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:43
 * @Purpose :碎片基类
 */
abstract class BaseFragment : Fragment() {

    abstract fun setContentView(): Int
    lateinit var mResultTo: ResultFragmentTo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setContentView(), container, false)
    }

    abstract fun setInitCreatedContentView(view: View, savedInstanceState: Bundle?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mResultTo = ResultFragmentTo(this.activity!!)
        setInitCreatedContentView(view, savedInstanceState)
    }

}