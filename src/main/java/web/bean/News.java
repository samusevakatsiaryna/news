package web.bean;

import java.io.Serializable;

public class News implements Serializable {

    private int id;
    private String title;
    private String content;
    private String type;
    private int typeId;

    public News(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public News(String title, String content, int typeId) {
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    public News(int id, String title, String content, int typeId, String type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
        this.type = type;
    }

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public News(int newsId, String title, String content, String typeName) {
        this.id = newsId;
        this.title = title;
        this.content = content;
        this.type = typeName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
