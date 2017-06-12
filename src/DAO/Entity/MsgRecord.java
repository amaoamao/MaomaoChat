package DAO.Entity;

import java.sql.Timestamp;

/**
 * Created by Jaho on 2017/5/23.
 */
public class MsgRecord {

    private String host;
    private String sender;
    private String receiver;
    private int receiver_type;
    private Timestamp dateTime;
    private String content;
    private int content_type;
    private boolean hasSent;

    public MsgRecord(){
        super();
    }

    public MsgRecord(String host, String sender, String receiver, int receiver_type, Timestamp dateTime, String content, int content_type, boolean hasSent) {
        this.host = host;
        this.sender = sender;
        this.receiver = receiver;
        this.receiver_type = receiver_type;
        this.dateTime = dateTime;
        this.content = content;
        this.content_type = content_type;
        this.hasSent = hasSent;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getReceiverType() {
        return receiver_type;
    }

    public void setReceiverType(int receiverType) {
        this.receiver_type = receiverType;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentType() {
        return content_type;
    }

    public void setContentType(int contentType) {
        this.content_type = contentType;
    }

    public boolean isHasSent() {
        return hasSent;
    }

    public void setHasSent(boolean hasSent) {
        this.hasSent = hasSent;
    }
}
