package com.backpacker.UtilsLibrary.kotlin

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern
import android.text.TextUtils
import java.util.*
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.view.ViewTreeObserver
import android.app.Activity
import android.app.Dialog
import android.system.Os.close
import android.view.WindowManager
import android.widget.*
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    工具类
 * @author: L-BackPacker
 * @date:   2019/3/31 22:23
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object Util {
    /**
     * 隐藏键盘
     *
     * @param context context
     * @param view    The currently focused view
     */
    fun hideInputMethod(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }

        val imm = context!!
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm!!.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }

    /**
     * 显示输入键盘
     *
     * @param context context
     * @param view    The currently focused view, which would like to receive soft
     * keyboard input
     */
    fun showInputMethod(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }

        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(view, 0)
    }



    /**
     * 判断是否为重复字符串
     *
     * @param str ，需要检查的字符串
     */
    fun isRepeatedStr(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val len = str.length
        if (len <= 1) {
            return false
        } else {
            val firstChar = str[0]// 第一个字长度
            for (i in 1 until len) {
                val nextChar = str[i]// 第i个字长度
                if (firstChar != nextChar) {
                    return false
                }
            }
            return true
        }
    }

    /**
     * 判断是否为纯数字
     *
     * @param str ，需要检查的字符串
     */
    fun isNumbericString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }

        val p = Pattern.compile("^[0-9]+$")// 从开头到结尾必须全部为数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否为纯字母
     *
     * @param str
     * @return
     */
    fun isAlphaBetaString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }

        val p = Pattern.compile("^[a-zA-Z]+$")// 从开头到结尾必须全部为字母或者数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否为纯字母或数字
     *
     * @param str
     * @return
     */
    fun isAlphaBetaNumbericString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val p = Pattern.compile("^[a-zA-Z0-9]+$")// 从开头到结尾必须全部为字母或者数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否包含中文
     *
     * @param str
     * @return
     */
    private val regEx = "[\u4e00-\u9fa5]"
    private val pat = Pattern.compile(regEx)
    fun isContainsChinese(str: String): Boolean {
        return pat.matcher(str).find()
    }

    /**
     * 模式匹配
     *
     * @param pattern
     * @param input
     * @return
     */
    fun patternMatcher(pattern: String, input: String): Boolean {
        if (TextUtils.isEmpty(pattern) || TextUtils.isEmpty(input)) {
            return false
        }
        val pat = Pattern.compile(pattern)
        val matcher = pat.matcher(input)

        return matcher.find()
    }

    /**
     * 获取随机数
     *
     * @return
     */
    fun getRandom8(): Long {
        val str = StringBuilder()//定义变长字符串
        val random = Random()
        //随机生成数字，并添加到字符串
        for (i in 0..7) {
            str.append(random.nextInt(10))
        }
        //将字符串转换为数字并输出
        return Integer.parseInt(str.toString()).toLong()
    }

    /**
     * 获取文件大小
     */
    fun convertFileSizeB(size: Long): String {
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024

        if (size >= gb) {
            return String.format("%.1f GB", size.toFloat() / gb)
        } else if (size >= mb) {
            val f = size.toFloat() / mb
            return String.format(if (f > 100) "%.0f MB" else "%.1f MB", f)
        } else if (size >= kb) {
            val f = size.toFloat() / kb
            return String.format(if (f > 100) "%.0f KB" else "%.1f KB", f)
        } else
            return String.format("%d B", size)
    }

    /**
     * 获取键盘是否弹出
     *
     * @param root
     * @return
     */
    fun getKeybordStatus(root: View): Boolean {
        val screenHeight = IntArray(1)
        val myHeight = IntArray(1)
        val heightDiff = IntArray(1)
        root.viewTreeObserver.addOnGlobalLayoutListener {
            screenHeight[0] = root.rootView.height
            myHeight[0] = root.height
            heightDiff[0] = screenHeight[0] - myHeight[0]
        }
        return heightDiff[0] > 100
    }

    /**
     * 设置背景颜色
     *
     * @param bgAlpha
     */
    fun setBackgroundAlpha(bgAlpha: Float, mContext: Context) {
        val lp = (mContext as Activity).window
            .attributes
        lp.alpha = bgAlpha
        mContext.window.attributes = lp
    }

    fun getArrayString(context: Context, id: Int): Array<String> {
        var lists = context.resources.getStringArray(id)
        return lists
    }

    fun getViewText(view: View?):String?{
        if(view==null)return "";
        return when (view) {
            is TextView -> (view as TextView).text.toString().trim()
            is Button -> (view as Button).text.toString().trim()
            is EditText -> (view as EditText).text.toString().trim()
            else -> ""
        }
    }
    /**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
    fun personIdValidation(text: String): Boolean {
        val regx = "[0-9]{17}x"
        val reg1 = "[0-9]{15}"
        val regex = "[0-9]{18}"
        return text.matches(regx.toRegex()) || text.matches(reg1.toRegex()) || text.matches(regex.toRegex())
    }


    fun getFormAssets(context: Context, fileName: String): String? {
        val stringBuilder = StringBuilder()
        BufferedReader(InputStreamReader(context.assets.open(fileName), "UTF-8")).run {
            var line: String? = ""
            do {
                line = readLine()
                if (line != null) {
                    stringBuilder.append(line)
                } else {
                    break
                    close()
                }
            } while (true)
            return stringBuilder.toString()
        }
        return null
    }
    private val MIN_CLICK_DELAY_TIME = 2000
    private var lastClickTime: Long = 0
    /***
     * 处理多次点击问题
     * @return
     */
    fun handleOnDoubleClick(): Boolean {
        val l = System.currentTimeMillis()
        if (l - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = l
            return false
        }
        return true
    }
    /**
     * 获取view 文字
     */
    fun getObjToStr(v: View): String {

        return when (v) {
            is TextView -> (v as TextView).text.toString().trim()
            is Button -> (v as Button).text.toString().trim()
            is EditText -> (v as EditText).text.toString().trim()
            else -> ""
        }
    }

    /**
     * 获取本地资源arry数据
     * @param id 名称
     */
    fun getMutablistString(context: Context, id: Int): MutableList<String> {
        val lists = context.resources.getStringArray(id)
        var mutableList = arrayListOf<String>()
        for (item in lists) {
            mutableList.add(item)
        }
        return mutableList
    }
    /**
     * 展示dialog
     * @param dialog
     */
    fun showDialog(dialog: Dialog?) {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
        }
    }

    /**
     * 取消dialog
     * @param dialog
     */
    fun dismissDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

}