package com.example.truyencuoi;

import java.io.Serializable;

public class StoryEntity implements Serializable {
    public String topic;
    public String title;
    public String content;

    public StoryEntity(String topic, String title, String content) {
        this.topic = topic;
        this.title = title;
        this.content = content;
    }
}
