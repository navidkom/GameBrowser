package ir.artapps.gamebrowser.ui.social

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.chat.Message
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.profile_item_custom_view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ChatFragment : BaseDialogFragment(),
    Toolbar.OnMenuItemClickListener, View.OnClickListener {

    val viewModel: SocialViewModel by viewModel()

    private var recyclerView: RecyclerView? = null
    private var editText: AppCompatEditText? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    var adapter = ChatAdapter(ArrayList())

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

        firebaseAnalytics = Firebase.analytics
        if (getView() == null) {
            return
        }
        val arguments = arguments

        signInBtn.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")
            firebaseAnalytics.logEvent("login_chat"){}
        }

        recyclerView = view.findViewById(R.id.chat_recycler_view)
        editText = view.findViewById(R.id.message_edit_text)
        editText = view.findViewById(R.id.message_edit_text)
        view.findViewById<View>(R.id.send_message).setOnClickListener(this)
        recyclerView?.addOnLayoutChangeListener(View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (recyclerView != null && recyclerView!!.adapter != null) recyclerView!!.scrollToPosition(
                0
            )
        })

        viewModel.chatLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { list ->
            Log.d("observe", "chatLiveData")

            adapter = ChatAdapter(list.map {  Message(it,
                it.participant?.coreUserId?.toLong() == viewModel.profileLiveData.value?.userId?.toLong()
            ) })
            recyclerView?.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
            recyclerView?.adapter = adapter
            recyclerView?.scrollToPosition(0)
//            progress?.visibility = View.GONE
            connectionStateParent.visibility = View.GONE
        })

        viewModel.chatStateLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                "CHAT_READY" -> {
                    connectionStateTV?.text = "Updating..."

                    Handler().postDelayed( Runnable {
                        connectionStateParent?.visibility = View.GONE
                    },  3000 )
                }
                "CLOSED" -> {
                    connectionStateParent?.visibility = View.VISIBLE
                    connectionStateTV?.text = "Disconnected"
                }
                "OPEN" -> {
                    connectionStateParent?.visibility = View.VISIBLE
                    connectionStateTV?.text = "Connecting..."
                }

                "ASYNC_READY" -> {
                    connectionStateParent?.visibility = View.VISIBLE
                    connectionStateTV?.text = "Connecting..."
                }
            }
        })


        viewModel.profileLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { response ->
                if (response == null) {
                    emptyView.visibility = View.VISIBLE
                    return@Observer
                }
                emptyView.visibility = View.GONE
            })
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.send_message -> {
                if (!message_edit_text.text.isNullOrEmpty()) {

                    if(viewModel.chatStateLiveData.value == "CHAT_READY"){
                        viewModel.sendMessage(message_edit_text.text.toString())
                        message_edit_text.text = null
                    } else {
                        Snackbar.make(requireView() , "ارسال پیام ممکن نیست ،لطفا اتصال خود را بررسی کنید", Snackbar.LENGTH_SHORT).show()

                    }
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