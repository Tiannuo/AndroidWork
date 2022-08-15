package com.work.theIsle

import com.work.supportlib.LoggerUtils
import java.io.IOException
import kotlin.jvm.Throws

/**
 * @Author TIKOU
 * @Date 2022/8/15-12:53
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class SpellBook {
    @JvmField()
    val spells = mapOf("A" to 3, "B" to 4)

    public fun spell() {

    }

    @JvmOverloads
    public fun spellUp(a: String = "a", b: String = "b") {
        LoggerUtils.i("spellUp $a & $b")
    }


    @Throws(IOException::class)
    fun accept() {

    }
}