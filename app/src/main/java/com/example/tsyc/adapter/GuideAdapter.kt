package com.example.tsyc.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Title:  tsyc
 * @Package com.example.tsyc.adapter
 * @Description:    引导也适配器
 * @author: L-BackPacker
 * @date:   2019/7/6 13:35
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class GuideAdapter(var fm: FragmentManager, var list: MutableList<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return if (list != null) list.size else 0
    }
}