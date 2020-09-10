package ir.artapps.gamebrowser.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.podchat.chat.Chat
import com.fanap.podchat.chat.ChatAdapter
import com.fanap.podchat.chat.bot.result_model.CreateBotResult
import com.fanap.podchat.chat.bot.result_model.DefineBotCommandResult
import com.fanap.podchat.chat.bot.result_model.StartStopBotResult
import com.fanap.podchat.chat.messge.ResultUnreadMessagesCount
import com.fanap.podchat.chat.pin.pin_message.model.ResultPinMessage
import com.fanap.podchat.chat.pin.pin_thread.model.ResultPinThread
import com.fanap.podchat.chat.thread.public_thread.RequestJoinPublicThread
import com.fanap.podchat.chat.thread.public_thread.ResultIsNameAvailable
import com.fanap.podchat.chat.thread.public_thread.ResultJoinPublicThread
import com.fanap.podchat.chat.user.profile.ResultUpdateProfile
import com.fanap.podchat.chat.user.user_roles.model.ResultCurrentUserRoles
import com.fanap.podchat.mainmodel.MessageVO
import com.fanap.podchat.mainmodel.ResultDeleteMessage
import com.fanap.podchat.model.*
import com.fanap.podchat.requestobject.RequestConnect
import com.fanap.podchat.requestobject.RequestGetContact
import com.fanap.podchat.requestobject.RequestGetHistory
import com.fanap.podchat.requestobject.RequestMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class ChatRepositoryImpl(context: Context, val podRepository: PodRepository) : ChatAdapter(),
    ChatRepository {

    private var messageList = mutableListOf<MessageVO>()
    val chat = Chat.init(context)
    var chatState: String? = "CLOSED"

    val serverAddress = "wss://chat-sandbox.pod.ir/ws"
    val appId = "POD-Chat"
    val severName = "chat-server"
    val ssoHost = "https://accounts.pod.ir"
    val platformHost = "https://sandbox.pod.ir:8043/srv/basic-platform/"
    val fileServer = "https://sandbox.pod.ir:8443"


    private val chatLiveData = MutableLiveData<List<MessageVO>>()

    private val chatStateLiveData = MutableLiveData<String>()

    init {
        chat.rawLog(true)
        chat.isLoggable(true)
        chat.addListener(this)
        schedule()
    }

//    override fun setNotification(activity: Activity) {
//        val notificationConfig: CustomNotificationConfig =
//            CustomNotificationConfig.Builder(activity)
//                .setChannelName("CHANNEL_NAME")
//                .setChannelId("CHANNEL_ID")
//                .setChannelDescription("Fanap soft podchat notification channel")
//                .setIcon(R.drawable.kidzylogo)
//                .setNotificationImportance(NotificationManager.IMPORTANCE_DEFAULT)
//                .build()
//
//        chat.setupNotification(notificationConfig)
//    }


    override fun shouldShowNotification(bool: Boolean) {
        chat.shouldShowNotification(bool)
    }

    private fun schedule() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Log.d("CHAT_SDK", "navid timer " + chatState)
                if (podRepository.token != null && (chatState == "CLOSED")) {
                    chat.closeChat()
                    val requestConnect = RequestConnect.Builder(
                        serverAddress,
                        appId,
                        severName,
                        podRepository.token,
                        ssoHost,
                        platformHost,
                        fileServer
                    ).build()

                    chat.connect(requestConnect)
                }
            }
        }, 0, 5000)
    }

    override fun getHistoryLiveData(): LiveData<List<MessageVO>> {
        return chatLiveData
    }

    override fun getChatStateLiveData(): LiveData<String> {
        return chatStateLiveData
    }

    override fun sendMessage(message: String) {

        val reqMessage = RequestMessage.Builder(message, 8716)
            .build();
        chat.sendTextMessage(reqMessage, null)
    }

    override fun onSent(content: String?, chatResponse: ChatResponse<ResultMessage>?) {
        super.onSent(content, chatResponse)
    }

    override fun onGetBlockList(content: String?, outPutBlockList: ChatResponse<ResultBlockList>?) {
        super.onGetBlockList(content, outPutBlockList)
    }

    override fun onUnPinMessage(response: ChatResponse<ResultPinMessage>?) {
        super.onUnPinMessage(response)
    }

    override fun onGetThreadAdmin(
        content: String?,
        chatResponse: ChatResponse<ResultParticipant>?
    ) {
        super.onGetThreadAdmin(content, chatResponse)
    }

    override fun onGetThreadAdmin(jsonData: String?) {
        super.onGetThreadAdmin(jsonData)
    }

    override fun OnDeliveredMessageList(
        content: String?,
        chatResponse: ChatResponse<ResultParticipant>?
    ) {
        super.OnDeliveredMessageList(content, chatResponse)
    }

    override fun onBlock(content: String?, outPutBlock: ChatResponse<ResultBlock>?) {
        super.onBlock(content, outPutBlock)
    }

    override fun onDeleteMessage(
        content: String?,
        outPutDeleteMessage: ChatResponse<ResultDeleteMessage>?
    ) {
        super.onDeleteMessage(content, outPutDeleteMessage)
    }

    override fun onUploadFile(content: String?, chatResponse: ChatResponse<ResultFile>?) {
        super.onUploadFile(content, chatResponse)
    }

    override fun onUnmuteThread(content: String?, outPutUnMute: ChatResponse<ResultMute>?) {
        super.onUnmuteThread(content, outPutUnMute)
    }

    override fun onSeen(content: String?, chatResponse: ChatResponse<ResultMessage>?) {
        super.onSeen(content, chatResponse)
    }

    override fun onGetThread(content: String?, thread: ChatResponse<ResultThreads>?) {
        super.onGetThread(content, thread)
    }

    override fun OnNotSeenDuration(resultNotSeen: OutPutNotSeenDurations?) {
        super.OnNotSeenDuration(resultNotSeen)
    }

    override fun onThreadAddParticipant(
        content: String?,
        outPutAddParticipant: ChatResponse<ResultAddParticipant>?
    ) {
        super.onThreadAddParticipant(content, outPutAddParticipant)
    }

    override fun onContactAdded(content: String?, chatResponse: ChatResponse<ResultAddContact>?) {
        super.onContactAdded(content, chatResponse)
    }

    override fun onEditedMessage(content: String?, chatResponse: ChatResponse<ResultNewMessage>?) {
        super.onEditedMessage(content, chatResponse)
    }

    override fun onGetHistory(content: String?, history: ChatResponse<ResultHistory>?) {
        super.onGetHistory(content, history)

        messageList.clear()
        history?.result?.history?.let {
            messageList.addAll(it)
            chatLiveData.postValue(it)
        }
    }

    override fun onPinThread(response: ChatResponse<ResultPinThread>?) {
        super.onPinThread(response)
    }

    override fun onTypingSignalTimeout(threadId: Long) {
        super.onTypingSignalTimeout(threadId)
    }

    override fun OnSeenMessageList(
        content: String?,
        chatResponse: ChatResponse<ResultParticipant>?
    ) {
        super.OnSeenMessageList(content, chatResponse)
    }

    override fun onUnPinThread(response: ChatResponse<ResultPinThread>?) {
        super.onUnPinThread(response)
    }

    override fun onMuteThread(content: String?, outPutMute: ChatResponse<ResultMute>?) {
        super.onMuteThread(content, outPutMute)
    }

    override fun onSyncContact(content: String?, chatResponse: ChatResponse<Contacts>?) {
        super.onSyncContact(content, chatResponse)
    }

    override fun onRemoveContact(content: String?, response: ChatResponse<ResultRemoveContact>?) {
        super.onRemoveContact(content, response)
    }

    override fun OnMapReverse(json: String?, chatResponse: ChatResponse<ResultMapReverse>?) {
        super.OnMapReverse(json, chatResponse)
    }

    override fun OnRemovedFromThread(content: String?, chatResponse: ChatResponse<ResultThread>?) {
        super.OnRemovedFromThread(content, chatResponse)
    }

    override fun onThreadLeaveParticipant(
        content: String?,
        response: ChatResponse<ResultLeaveThread>?
    ) {
        super.onThreadLeaveParticipant(content, response)
    }

    override fun onUnBlock(content: String?, outPutBlock: ChatResponse<ResultBlock>?) {
        super.onUnBlock(content, outPutBlock)
    }

    override fun handleCallbackError(cause: Throwable?) {
        super.handleCallbackError(cause)
    }

    override fun OnStaticMap(chatResponse: ChatResponse<ResultStaticMapImage>?) {
        super.OnStaticMap(chatResponse)
    }

    override fun onMapSearch(content: String?, outPutMapNeshan: OutPutMapNeshan?) {
        super.onMapSearch(content, outPutMapNeshan)
    }

    override fun onLowFreeSpace(bytesAvailable: Long) {
        super.onLowFreeSpace(bytesAvailable)
    }

    override fun onUpdateThreadInfo(
        threadJson: String?,
        chatResponse: ChatResponse<ResultThread>?
    ) {
        super.onUpdateThreadInfo(threadJson, chatResponse)
    }

    override fun onUserInfo(content: String?, outPutUserInfo: ChatResponse<ResultUserInfo>?) {
        super.onUserInfo(content, outPutUserInfo)

        Log.d("CHAT_SDK", "navid " + outPutUserInfo.toString())
    }

    override fun onCreateThread(response: ChatResponse<ResultThread>?) {
        super.onCreateThread(response)
    }

    override fun onChatProfileUpdated(response: ChatResponse<ResultUpdateProfile>?) {
        super.onChatProfileUpdated(response)
    }

    override fun onJoinPublicThread(response: ChatResponse<ResultJoinPublicThread>?) {
        super.onJoinPublicThread(response)


    }

    override fun onUniqueNameIsAvailable(response: ChatResponse<ResultIsNameAvailable>?) {
        super.onUniqueNameIsAvailable(response)
    }

    override fun onContactsLastSeenUpdated(response: ChatResponse<ResultNotSeenDuration>?) {
        super.onContactsLastSeenUpdated(response)
    }

    override fun onBotStopped(response: ChatResponse<StartStopBotResult>?) {
        super.onBotStopped(response)
    }

    override fun onBotStarted(response: ChatResponse<StartStopBotResult>?) {
        super.onBotStarted(response)
    }

    override fun onBotCreated(response: ChatResponse<CreateBotResult>?) {
        super.onBotCreated(response)
    }

    override fun onGetUnreadMessagesCount(response: ChatResponse<ResultUnreadMessagesCount>?) {
        super.onGetUnreadMessagesCount(response)
    }

    override fun onBotCommandsDefined(response: ChatResponse<DefineBotCommandResult>?) {
        super.onBotCommandsDefined(response)
    }

    override fun OnClearHistory(content: String?, chatResponse: ChatResponse<ResultClearHistory>?) {
        super.OnClearHistory(content, chatResponse)
    }

    override fun onSignalMessageReceived(output: OutputSignalMessage?) {
        super.onSignalMessageReceived(output)
    }

    override fun onSignalMessageReceived(result: ChatResponse<ResultSignalMessage>?) {
        super.onSignalMessageReceived(result)
    }

    override fun onCreateThread(content: String?, outPutThread: ChatResponse<ResultThread>?) {
        super.onCreateThread(content, outPutThread)
    }

    override fun onUpdateContact(
        content: String?,
        chatResponse: ChatResponse<ResultUpdateContact>?
    ) {
        super.onUpdateContact(content, chatResponse)
    }

    override fun onNewMessage(content: String?, outPutNewMessage: ChatResponse<ResultNewMessage>?) {
        super.onNewMessage(content, outPutNewMessage)

        outPutNewMessage?.result?.messageVO?.let {
            messageList.add(0, it)
            chatLiveData.postValue(messageList)
        }
    }

    override fun onLogEvent(log: String?) {
        super.onLogEvent(log)
    }

    override fun onLogEvent(logName: String?, json: String?) {
        super.onLogEvent(logName, json)
    }

    override fun onGetCurrentUserRoles(response: ChatResponse<ResultCurrentUserRoles>?) {
        super.onGetCurrentUserRoles(response)
    }

    override fun onDeliver(content: String?, chatResponse: ChatResponse<ResultMessage>?) {
        super.onDeliver(content, chatResponse)
    }

    override fun onUploadImageFile(content: String?, chatResponse: ChatResponse<ResultImageFile>?) {
        super.onUploadImageFile(content, chatResponse)
    }

    override fun onThreadInfoUpdated(content: String?, response: ChatResponse<ResultThread>?) {
        super.onThreadInfoUpdated(content, response)
    }

    override fun onThreadRemoveParticipant(
        content: String?,
        chatResponse: ChatResponse<ResultParticipant>?
    ) {
        super.onThreadRemoveParticipant(content, chatResponse)
    }

    override fun onPinMessage(response: ChatResponse<ResultPinMessage>?) {
        super.onPinMessage(response)
    }

//    override fun onLastSeenUpdated(content: String?) {
//        super.onLastSeenUpdated(content)
//    }

    override fun onGetMentionList(response: ChatResponse<ResultHistory>?) {
        super.onGetMentionList(response)
    }

    override fun onSearchContact(content: String?, chatResponse: ChatResponse<ResultContact>?) {
        super.onSearchContact(content, chatResponse)
    }

    override fun onChatState(state: String?) {
        super.onChatState(state)

        chatStateLiveData.postValue(state)
        chatState = state
        Log.d("CHAT_SDK", "navid " + state)
        when (state) {
            "CHAT_READY" -> {
                val count = 50L
                val offset = 0L

                val requestGetContact = RequestGetContact.Builder()
                    .count(count)
                    .offset(offset)
                    .build()
                chat.getContacts(requestGetContact, null)

            }
        }
    }

    override fun onRemoveRoleFromUser(outputSetRoleToUser: ChatResponse<ResultSetAdmin>?) {
        super.onRemoveRoleFromUser(outputSetRoleToUser)
    }

    override fun onRenameThread(content: String?, outPutThread: OutPutThread?) {
        super.onRenameThread(content, outPutThread)
    }

    override fun onGetThreadParticipant(
        content: String?,
        outPutParticipant: ChatResponse<ResultParticipant>?
    ) {
        super.onGetThreadParticipant(content, outPutParticipant)
    }

    override fun onGetThreadParticipant(outPutParticipant: ChatResponse<ResultParticipant>?) {
        super.onGetThreadParticipant(outPutParticipant)
    }

    override fun OnSetRule(outputSetRoleToUser: ChatResponse<ResultSetAdmin>?) {
        super.OnSetRule(outputSetRoleToUser)
    }

    override fun onMapRouting(content: String?) {
        super.onMapRouting(content)
    }

    override fun onGetContacts(content: String?, outPutContact: ChatResponse<ResultContact>?) {
        super.onGetContacts(content, outPutContact)

        val request = RequestJoinPublicThread.Builder("kidzy").build()
        chat.joinPublicThread(request)

//        val requestThread = RequestThread.Builder()
//            .build();

//        chat.getThreads(requestThread, null)

//        val requestJoinPublicThread =  RequestJoinPublicThread.Builder("kidzy")
//            .build();
//        chat.joinPublicThread(requestJoinPublicThread)

//        val requestCreatePublicThread = RequestCreatePublicThread.Builder(2, listOf(Invitee(18136, 2)), "kidzy").build()
//        chat.createThread(requestCreatePublicThread)

        val requestGetHistory = RequestGetHistory.Builder(8716)
            .build();
        requestGetHistory.count = 20
        chat.getHistory(requestGetHistory, null)
    }

    override fun onError(content: String?, error: ErrorOutPut?) {
        super.onError(content, error)

        error?.errorCode?.let {
            if (it == 21.toLong()) {
                GlobalScope.launch {
                    podRepository.updateToken()
                    chat.closeChat()

                    val requestConnect = RequestConnect.Builder(
                        serverAddress,
                        appId,
                        severName,
                        podRepository.token,
                        ssoHost,
                        platformHost,
                        fileServer
                    ).build()

                    chat.connect(requestConnect)
                }
            }
        }
    }

    override fun OnSignalMessageReceive(output: OutputSignalMessage?) {
        super.OnSignalMessageReceive(output)
    }
}