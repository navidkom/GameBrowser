package ir.artapps.gamebrowser.ui.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fanap.gameCenter.TIS.Base.EventCallback
import com.fanap.gameCenter.TIS.Base.RequestCallback
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.R
import org.json.JSONObject


class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signin)
//
//        if (params.has("deviceId") && !params.isNull("deviceId")) {
//            this.deviceId = params.getString("context")
//        } else {
//            this.context = params.get("context") as Context
//        }
//
//        if (params.has("serviceMode") && !params.isNull("serviceMode")) {
//            val mode: String = params.getString("serviceMode")
//            if (mode == "developMode_local" || mode == "developMode_online") {
//                ConfigData.serviceMode = mode
//                ConfigData.isLocal = true
//                ConfigData.init(null as JSONObject?)
//            }
//        } else if (params.has("isLocal") && !params.isNull("isLocal")) {
//            ConfigData.isLocal = params.getBoolean("isLocal")
//            ConfigData.init(null as JSONObject?)
//        }
//
//        this.registerGame(params)
//        if (params.has("loginData") && !params.isNull("loginData")) {
//            this.loginData = params.getJSONObject("loginData")
//            Service.log.info("loginData -- " + this.loginData)
//        }


        val reqData = JSONObject()
        reqData.put("context", this)


        val service = Service(reqData)
        service.on("ready", object : EventCallback() {
            override fun onFire(msg: JSONObject) {
                super.onFire(msg)
                //  if user has account call login data
//                isReady = true
//                onsetDataLogin()
//                onSetOfflineMode()
//                readyState.postValue(true)



//                "id":394218,"name":"navidkom","token":"ddefe52b44a84324897255b2670d2d91","tokenIssuer":0,"guest":false,"ssoLogin":true,"thing":false}

                val loginData = JSONObject()
                loginData.put("id", 394218)
                loginData.put("token", "ddefe52b44a84324897255b2670d2d91")
                loginData.put("tokenIssuer", 0)
                loginData.put("name", "navidkom")
                service.initLogin(
                    loginData,
                    object : RequestCallback() {
                        override fun onResult(result: JSONObject) {
                            println("getEnrolledLeagues method : $result")
                        }
                    })


                val gameData = JSONObject()
                gameData.put("id", 8204)
                service.getGamesInfo(
                    gameData,
                    object : RequestCallback() {
                        override fun onResult(result: JSONObject) {
                            println("getEnrolledLeagues method : $result")
                        }
                    })


            }
        })

    }

}