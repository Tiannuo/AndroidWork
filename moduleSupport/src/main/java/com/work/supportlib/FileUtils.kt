package com.work.supportlib

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.text.TextUtils
import android.util.Base64
import java.io.*

/**
 * @author zhaojian
 * @time 2018/7/6 14:49
 * @describe
 */
object FileUtils {
    /**
     * 获取项目存储路径
     * @param context
     * @return
     */
    fun getAppFilePath(context: Context): String {
        //return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context.getPackageName();
        return context.filesDir.toString()
    }

    fun getFormPath(context: Context): String {
        return getAppFilePath(context) + File.separatorChar + "form/"
    }

    fun getImageDirPath(context: Context): String {
        return getAppFilePath(context) + File.separatorChar + "img/"
    }


    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param buffer   buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    @Throws(IOException::class)
    private fun readToBuffer(buffer: StringBuffer, filePath: String) {
        val `is`: InputStream = FileInputStream(filePath)
        var line: String // 用来保存每行读取的内容
        val reader = BufferedReader(InputStreamReader(`is`))
        line = reader.readLine() // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line) // 将读到的内容添加到 buffer 中
            buffer.append("\n") // 添加换行符
            line = reader.readLine() // 读取下一行
        }
        reader.close()
        `is`.close()
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     */
    @Throws(IOException::class)
    fun readFile(filePath: String): String {
        val sb = StringBuffer()
        readToBuffer(sb, filePath)
        return sb.toString()
    }


    @SuppressLint("NewApi")
    fun bitmapToBase64(bitmap: Bitmap?): String? {

        // 要返回的字符串
        var reslut: String? = null
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                /**
                 * 压缩只对保存有效果bitmap还是原来的大小
                 */
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                baos.flush()
                baos.close()
                // 转换为字节数组
                val byteArray = baos.toByteArray()

                // 转换为字符串
                reslut = Base64.encodeToString(byteArray, Base64.DEFAULT)
            } else {
                return null
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                baos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return reslut
    }


    /*存储随访照片路径*/
    fun getAppPhotoPath(context: Context): String {
        val dir =
            Environment.getExternalStorageDirectory().absolutePath + "/" + context.packageName + "/.nomedia/photos/"
        val file = File(dir)
        if (!file.exists()) {
            file.mkdirs()
        }
        return dir
    }

    /*存储照片编辑路径*/
    fun getAppPhotoEditPath(context: Context): String {
        val dir =
            Environment.getExternalStorageDirectory().absolutePath + "/" + context.packageName + "/.nomedia/photos/edit/"
        val file = File(dir)
        if (!file.exists()) {
            file.mkdirs()
        }
        return dir
    }

    /*存储照片编辑路径*/
    fun getAppVideoPath(context: Context): String {
        val dir =
            Environment.getExternalStorageDirectory().absolutePath + "/" + context.packageName + "/.nomedia/videos/"
        val file = File(dir)
        if (!file.exists()) {
            file.mkdirs()
        }
        return dir
    }

    /*存储音频路径*/
    fun getAppAudioPath(context: Context): String {
        val dir =
            Environment.getExternalStorageDirectory().absolutePath + "/" + context.packageName + "/.nomedia/audio/"
        val file = File(dir)
        if (!file.exists()) {
            file.mkdirs()
        }
        return dir
    }

    /*删除文件*/
    fun deleteFileIfExist(file: File?): Boolean {
        if (file == null) {
            return false
        }
        return if (file.exists()) {
            file.delete()
        } else false
    }

    /**
     * 删除文件，如果是文件夹，则删除文件夹下所有文件
     */
    fun deleteFileWithPath(path: String?) {
        if (TextUtils.isEmpty(path)) {
            return
        }
        val file = File(path)
        if (!file.exists()) {
            return
        }
        if (file.isDirectory) {
            val files = file.listFiles()
            for (i in files.indices) {
                val f = files[i]
                deleteFileIfExist(f)
            }
            file.delete() //如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete()
        }
    }

    fun deleteFiles(fileList: List<File?>?) {
        if (fileList == null || fileList.isEmpty()) {
            return
        }
        for (file in fileList) {
            deleteFileIfExist(file)
        }
    }

    fun deleteFileIfExist(path: String?): Boolean {
        if (TextUtils.isEmpty(path)) {
            return false
        }
        val file = File(path)
        return if (file.exists()) {
            file.delete()
        } else false
    }

    val sDRootDirectory: File?
        get() {
            if (isSDMounted) {
                val file = Environment.getExternalStorageDirectory()
                file.mkdir()
                return file
            }
            return null
        }
    val isSDMounted: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            Environment.getExternalStorageDirectory()
            return Environment.MEDIA_MOUNTED == state
        }
}