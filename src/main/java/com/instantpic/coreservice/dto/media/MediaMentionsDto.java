package com.instantpic.coreservice.dto.media;

import java.util.ArrayList;
import java.util.List;

public class MediaMentionsDto {

    private List<String> mentions;
    private int count;

    public MediaMentionsDto() {
        mentions = new ArrayList<String>();
        count = 0;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
