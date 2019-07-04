package com.backpacker.UtilsLibrary.kotlin

import android.widget.EditText
import android.app.Activity
import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import java.util.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:00
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object EditeTextUtil {
    /**
     * EditText获取焦点并显示软键盘
     */
    fun showSoftInputFromWindow(activity: Activity, editText: EditText) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val inputManager = activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(editText, 0)
            }
        }, 300)
    }

    /**
     * 设置输入密码可现实隐藏
     * @param mChbLoginEyable 控制显示隐藏的按钮
     * @param editText 显示隐藏控件
     */
    fun showPassWord(mChbLoginEyable: CheckBox, editText: EditText) {
        mChbLoginEyable.setOnCheckedChangeListener { buttonView, isChecked ->
            showPassWord(isChecked, editText)
        }
    }

    /**
     * 是否显示明文还是密码
     */
    fun showPassWord(mChbLoginEyable: Boolean, editText: EditText) {
        if (mChbLoginEyable) {
            //选择状态 显示明文--设置为可见的密码
            //mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            /**
             * 第二种
             */
            /**
             * 第二种
             */
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            //mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            /**
             * 第二种
             */
            /**
             * 第二种
             */
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    private var clickSendListener: onKeySendClickListener? = null

    interface onKeySendClickListener {
        fun onSendClickListener()
    }

    fun setSendClickListener(clickListener: onKeySendClickListener) {
        this.clickSendListener = clickListener
    }

    fun setEditTextSendKey(editText: EditText) {
        editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (clickSendListener != null) {
                        clickSendListener!!.onSendClickListener()
                        return true
                    }
                }
                return false
            }
        })
    }

}