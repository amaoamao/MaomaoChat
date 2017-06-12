package DTO.Message;

import java.sql.Timestamp;

/**
 * Created by Jaho on 2017/5/24.
 * Message 类的属性
 */
public class Content {

    private int type;
    private String content;
    private Timestamp dateTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}

