package ir.artapps.gamebrowser.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanap.podchat.chat.Chat
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.chat.Message
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.Socket
import java.util.*

class ChatFragment : BaseDialogFragment(),
    Toolbar.OnMenuItemClickListener, View.OnClickListener {

    val viewModel : SocialViewModel by viewModel()

    private var recyclerView: RecyclerView? = null
    private var editText: AppCompatEditText? = null
    private val chat: Chat? = null
    private val mSocket: Socket? = null

    //    private NetworkRepository networkRepository = NetworkRepositoryImpl.getInstance();
    private var progress: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dummy
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
        if (arguments != null) {
//            if (arguments.containsKey(projectsModelKey)) {
//                model = arguments.getParcelable(projectsModelKey);
//            }
//        }
//        val toolbar: Toolbar = getView()!!.findViewById(R.id.toolbar)
//        if (toolbar != null) {

//            TextView titleName = toolbar.findViewById(R.id.chat_title_name);

//            if (chat != null && chat.getLastSender() != null) {
//                titleName.setText(chat.getTitle());
//            } else {
//                titleName.setText("پژوهشگر");
//            }
//            toolbar.setTitleTextColor(resources.getColor(R.color.white))
//            toolbar.setNavigationIcon(R.drawable.ic_nav_back)
//            toolbar.setNavigationOnClickListener { dismiss() }
//            toolbar.setOnMenuItemClickListener(this)
        }
        recyclerView = view.findViewById(R.id.chat_recycler_view)
        editText = view.findViewById(R.id.message_edit_text)
        editText = view.findViewById(R.id.message_edit_text)
        messages
        view.findViewById<View>(R.id.send_message).setOnClickListener(this)
        recyclerView?.addOnLayoutChangeListener(View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (recyclerView != null && recyclerView!!.adapter != null) recyclerView!!.scrollToPosition(
                0
            )
        })
    }

    private val messages: Unit
        private get() {
            val items: List<Message?> =
                ArrayList<Message?>()
            val adapter =
                ChatAdapter(items)
            recyclerView?.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
            recyclerView?.adapter = adapter
            recyclerView?.scrollToPosition(0)
            progress?.visibility = View.GONE
        }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.send_message -> {
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