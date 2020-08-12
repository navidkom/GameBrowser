package ir.artapps.gamebrowser.ui.social

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanap.podchat.chat.Chat
import com.fanap.podchat.mainmodel.MessageVO
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.Socket
import java.util.*

class ChatFragment : BaseDialogFragment(),
    Toolbar.OnMenuItemClickListener, View.OnClickListener {

    val viewModel: SocialViewModel by viewModel()

    private var recyclerView: RecyclerView? = null
    private var editText: AppCompatEditText? = null
    private val chat: Chat? = null
    private val mSocket: Socket? = null

    val items: ArrayList<MessageVO?> =
        ArrayList<MessageVO?>()

    var adapter = ChatAdapter(ArrayList())

    //    private NetworkRepository networkRepository = NetworkRepositoryImpl.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (getView() == null) {
            return
        }
        val arguments = arguments

        recyclerView = view.findViewById(R.id.chat_recycler_view)
        editText = view.findViewById(R.id.message_edit_text)
        editText = view.findViewById(R.id.message_edit_text)
        view.findViewById<View>(R.id.send_message).setOnClickListener(this)
        recyclerView?.addOnLayoutChangeListener(View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (recyclerView != null && recyclerView!!.adapter != null) recyclerView!!.scrollToPosition(
                0
            )
        })

        viewModel.chatLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            Log.d("observe", "chatLiveData")

            adapter =
                ChatAdapter(it)
            recyclerView?.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
            recyclerView?.adapter = adapter
            recyclerView?.scrollToPosition(0)
            progress?.visibility = View.GONE

        })

        viewModel.profileLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { response ->
                if (response == null) {
                    signinParent.visibility = View.VISIBLE
                    chatParent.visibility = View.GONE
                    return@Observer
                }

                signinParent.visibility = View.GONE
                chatParent.visibility = View.VISIBLE

            })
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.send_message -> {
                if (!message_edit_text.text.isNullOrEmpty()) {
                    viewModel.sendMessage(message_edit_text.text.toString())
                    message_edit_text.text = null
                }
            }
        }
    }

    companion object {
        private const val URL = "http://5.253.27.108"
        private const val projectsModelKey = "projectsModel"
        fun newInstance(): ChatFragment {
            val chatFragment = ChatFragment()
            val bundle = Bundle()
            //        bundle.putParcelable(projectsModelKey , projectsModel);
            chatFragment.arguments = bundle
            return chatFragment
        }
    }
}