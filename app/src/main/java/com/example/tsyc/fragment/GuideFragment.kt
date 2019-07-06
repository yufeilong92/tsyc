package com.example.tsyc.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.backpacker.UtilsLibrary.kotlin.SetUtils

import com.example.tsyc.R
import com.example.tsyc.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_guide.*

/**
 * @Title:
 * @Package com.example.tsyc.fragment
 * @Description: 引导页
 * @author: L-BackPacker
 * @date:   2019/7/6 13:41
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019/7/6
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GuideFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun setContentView(): Int {
        return R.layout.fragment_guide
    }

    override fun setInitCreatedContentView(view: View, savedInstanceState: Bundle?) {
        when (param1) {
            "0" -> {
                SetUtils.setImagerSrc(iv_guide_content, R.mipmap.timg)
            }
            "1" -> {
                SetUtils.setImagerSrc(iv_guide_content, R.mipmap.timg)

            }
            "2" -> {
                SetUtils.setImagerSrc(iv_guide_content, R.mipmap.timg)

            }
        }
    }


}
