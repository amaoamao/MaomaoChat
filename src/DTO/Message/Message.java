package DTO.Message;

/**
 * Created by Jaho on 2017/5/24.
 * 进行聊天消息传输的实体
 */
public class Message {

    private String sender;
    private Receiver receiver;
    private Content message;

    public Message() {
        this.sender = "";
        this.receiver = new Receiver();
        this.message = new Content();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Content getMessage() {
        return message;
    }

    public void setMessage(Content message) {
        this.message = message;
    }
}

