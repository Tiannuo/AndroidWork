package com.work.theIsle.openSourceFramework.glide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.work.theIsle.constant.Constant

/**
 * @Author TIKOU
 * @Date 2022/7/24-12:45
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class GlideViewModel : ViewModel() {
    private var imgUrlMutableLiveData = MutableLiveData<String>()

    public fun setImgUrlMutableLiveData(imgUrlMutableLiveData: MutableLiveData<String>) {
        this.imgUrlMutableLiveData = imgUrlMutableLiveData
    }

    public fun setImageUrl(url: String) {
        setImgUrlMutableLiveData(MutableLiveData(url))
    }

    public fun getImageUrl() = imgUrlMutableLiveData.value

    public fun getImgUrlMutableLiveData() = imgUrlMutableLiveData


    private var glideBeanMutableLiveData: MutableLiveData<GlideBean>? = null

    public fun setGlideBeanMutableLiveData(glideBeanMutableLiveData: MutableLiveData<GlideBean>) {
        this.glideBeanMutableLiveData = glideBeanMutableLiveData
    }

    public fun getGlideBeanMutableLiveData(): MutableLiveData<GlideBean> {
        if (glideBeanMutableLiveData == null) {
            glideBeanMutableLiveData = MutableLiveData()
            glideBeanMutableLiveData!!.value = GlideBean("")
        }
        return glideBeanMutableLiveData!!
    }

    public fun notifyImgData() {
        getGlideBeanMutableLiveData().apply {
            this.value!!.imgUrl = Constant.IMG_URL
            this.value = this.value
        }
    }
}