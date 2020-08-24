package ir.artapps.gamebrowser.entities.chat;

import com.fanap.podchat.mainmodel.MessageVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Navid Komijani
 * on 16,July,2019
 */
public class Message extends MessageVO {
     public MessageVO message = null;
     public boolean isMine = false;

    public Message(MessageVO vo , Boolean isMine){
        message = vo;
        this.isMine = isMine;
    }
}
