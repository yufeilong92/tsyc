package com.example.tsyc

import android.os.Bundle
import com.example.tsyc.base.BaseSelectImageActivity
import kotlinx.android.synthetic.main.activity_select_imager.*

class SelectImagerActivity : BaseSelectImageActivity() {

    override fun setInitContentView(): Int {
        return R.layout.activity_select_imager
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
        super.onInitCreateView(savedInstanceState)
        btn_select.setOnClickListener {
            toShowDialog()
        }

    }
}
