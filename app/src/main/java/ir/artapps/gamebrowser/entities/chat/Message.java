package ir.artapps.gamebrowser.entities.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Navid Komijani
 * on 16,July,2019
 */
public class Message {
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("document_id")
    @Expose
    private String documentId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("message_id")
    @Expose
    private String messageId;
    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("sent_at")
    @Expose
    private String sentAt;
    @SerializedName("is_mine")
    @Expose
    private Boolean isMine;

    public Boolean getMine() {
        return isMine;
    }

    public void setMine(Boolean mine) {
        isMine = mine;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
