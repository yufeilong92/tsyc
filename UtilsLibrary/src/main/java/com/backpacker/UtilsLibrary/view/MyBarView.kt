package com.backpacker.UtilsLibrary.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.example.UtilsLibrary.R


class MyBarView:View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        var tda: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.MyBarStyle)

        val barbg = tda.getColor(R.styleable.MyBarStyle_barBg,Color.TRANSPARENT)

        setBackgroundColor(barbg)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            0,
            0
        )
    }

}