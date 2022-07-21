package com.work.theIsle.jetpack.bean

/**
 * @Author TIKOU
 * @Date 2022/7/21-15:45
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class ScoreBean() {
    private var aTeamScore: Int = 0
    private var bTeamScore: Int = 0

    constructor(aTeamScore: Int?, bTeamScore: Int?) : this() {
        if (aTeamScore != null) {
            this.aTeamScore = aTeamScore
        }
        if (bTeamScore != null) {
            this.bTeamScore = bTeamScore
        }
    }

    public fun getATeamScore(): Int {
        return aTeamScore
    }

    public fun setATeamScore(score: Int) {
        this.aTeamScore = score
    }

    public fun getBTeamScore(): Int {
        return bTeamScore
    }

    public fun setBTeamScore(score: Int) {
        this.bTeamScore = score
    }
}