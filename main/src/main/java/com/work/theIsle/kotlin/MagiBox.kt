package com.work.theIsle.kotlin

/**
 * @Author TIKOU
 * @Date 2022/8/15-13:32
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class MagiBox<T : Human> {

    inline fun <reified T> randomDataOrBack(randomData: (name: String, age: Int) -> T): T {
        val items = listOf(Boy("jack", 15), Man("rose", 25))
        val randomIndex = items.shuffled().first()
        return if (randomIndex is T) {
            randomIndex
        } else {
            randomData("else", 1)
        }
    }

}

