package ir.artapps.gamebrowser.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fanap.podchat.chat.Chat
import com.fanap.podchat.requestobject.RequestConnect
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R

class SocialFragment private constructor(): Fragment() {

    companion object {
        fun newInstance() = SocialFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chat = Chat.init(context)

        val serverAddress = "wss://chat-sandbox.pod.ir/ws"
        val appId = "POD-Chat"
        val severName = "chat-server"
        val ssoHost = "https://accounts.pod.ir"
        val platformHost = "https://sandbox.pod.ir:8043/srv/basic-platform/"
        val fileServer = "https://sandbox.pod.ir:8443"

        val requestConnect = RequestConnect.Builder(
            serverAddress,
            appId,
            severName,
            App.token ,
            ssoHost,
            platformHost,
            fileServer
        )
            .build()
        chat.connect(requestConnect)
        chat.rawLog( true)
        chat.isLoggable(true)
    }
}