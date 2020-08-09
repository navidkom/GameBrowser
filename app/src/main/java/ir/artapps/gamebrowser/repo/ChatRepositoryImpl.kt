package ir.artapps.gamebrowser.repo

import android.content.Context
import androidx.lifecycle.Observer
import com.fanap.podchat.chat.Chat
import com.fanap.podchat.chat.ChatAdapter
import com.fanap.podchat.chat.pin.pin_message.model.ResultPinMessage
import com.fanap.podchat.chat.pin.pin_thread.model.ResultPinThread
import com.fanap.podchat.chat.user.user_roles.model.ResultCurrentUserRoles
import com.fanap.podchat.mainmodel.ResultDeleteMessage
import com.fanap.podchat.model.*
import com.fanap.podchat.requestobject.RequestConnect
import ir.artapps.gamebrowser.App

class ChatRepositoryImpl(context: Context) : ChatAdapter(), ChatRepository {

    val chat = Chat.init(context)

    val serverAddress = "wss://chat-sandbox.pod.ir/ws"
    val appId = "POD-Chat"
    val severName = "chat-server"
    val ssoHost = "https://accounts.pod.ir"
    val platformHost = "https://sandbox.pod.ir:8043/srv/basic-platform/"
    val fileServer = "https://sandbox.pod.ir:8443"


    init {
        App.profile.observeForever {
            val requestConnect = RequestConnect.Builder(
                serverAddress,
                appId,
                severName,
                App.token,
                ssoHost,
                platformHost,
                fileServer
            )
                .build()
            chat.connect(requestConnect)
            chat.rawLog(true)
            chat.isLoggable(true)
        }

        chat.addListener(this)

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

    override fun onLastSeenUpdated(content: String?) {
        super.onLastSeenUpdated(content)
    }

    override fun onGetMentionList(response: ChatResponse<ResultHistory>?) {
        super.onGetMentionList(response)
    }

    override fun onSearchContact(content: String?, chatResponse: ChatResponse<ResultContact>?) {
        super.onSearchContact(content, chatResponse)
    }

    override fun onChatState(state: String?) {
        super.onChatState(state)
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
    }

    override fun onError(content: String?, error: ErrorOutPut?) {
        super.onError(content, error)
    }

    override fun OnSignalMessageReceive(output: OutputSignalMessage?) {
        super.OnSignalMessageReceive(output)

    }
}