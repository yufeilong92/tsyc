package com.backpacker.UtilsLibrary.kotlin

import java.io.File
import java.nio.file.Files.delete
import java.nio.file.Files.isDirectory
import java.nio.file.Files.exists



/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:07
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object FileUtil {
    /**
     * 删除文件
     * @param path
     * @return
     */
    fun delectFile(path: File): Boolean {
        if (path.exists()) {
            if (path.isFile) {
                path.delete()//如果是文件，直接删除
            } else if (path.isDirectory) {
                val files = path.listFiles()
                for (i in files.indices) {
                    files[i].delete()
                }
            }
            path.delete()
            return true
        }
        return false
    }
}