package com.example.dologinout

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "836268d49ec3ac5878300d6973c2bb1c")
    }
}