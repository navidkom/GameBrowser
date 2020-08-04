package ir.artapps.gamebrowser.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanap.podchat.chat.Chat;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import ir.artapps.gamebrowser.R;
import ir.artapps.gamebrowser.base.BaseDialogFragment;
import ir.artapps.gamebrowser.entities.chat.Message;

public class ChatFragment extends BaseDialogFragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final String URL = "http://5.253.27.108";
    private static String projectsModelKey = "projectsModel";
    private RecyclerView recyclerView;
    private AppCompatEditText editText;
    private Chat chat;
    private Socket mSocket;
//    private NetworkRepository networkRepository = NetworkRepositoryImpl.getInstance();
    private ProgressBar progressBar;

    public static ChatFragment newInstance() {
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
//        bundle.putParcelable(projectsModelKey , projectsModel);
        chatFragment.setArguments(bundle);
        return chatFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_chat, container, false);
        return mainView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getView() == null) {
            return;
        }

        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(projectsModelKey)) {
//                model = arguments.getParcelable(projectsModelKey);
            }
        }

        progressBar = getView().findViewById(R.id.progress);

        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        if (toolbar != null) {

//            TextView titleName = toolbar.findViewById(R.id.chat_title_name);

//            if (chat != null && chat.getLastSender() != null) {
//                titleName.setText(chat.getTitle());
//            } else {
//                titleName.setText("پژوهشگر");
//            }
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar.setNavigationIcon(R.drawable.ic_nav_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            toolbar.setOnMenuItemClickListener(this);
        }

        recyclerView = view.findViewById(R.id.chat_recycler_view);
        editText = view.findViewById(R.id.message_edit_text);

        editText = view.findViewById(R.id.message_edit_text);

        getMessages();

        view.findViewById(R.id.send_message).setOnClickListener(this);

        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (recyclerView != null && recyclerView.getAdapter() != null)
                    recyclerView.scrollToPosition(0);
            }
        });
    }

    private void getMessages() {

        final List<Message> items = new ArrayList();

        ChatAdapter adapter = new ChatAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);

        progressBar.setVisibility(View.GONE);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_message:

//                SendMessage message = new SendMessage();
//                message.setMessage(editText.getText().toString());
//
//                networkRepository.sendMessage(chat.getChatId(), message, new CustomNetworkRespone<>(GeneralResponseModel.class, new CustomNetworkCallbak<GeneralResponseModel>() {
//                    @Override
//                    public void response(GeneralResponseModel generalResponseModel) {
//
//                        if( ! isAdded()){
//                            return;
//                        }
//
//                        getMessages();
//                        editText.setText("");
//                    }
//
//                    @Override
//                    public void failure(int code, String s) {
//
//                    }
//                }));
//                break;
//        }
        }

    }
}
