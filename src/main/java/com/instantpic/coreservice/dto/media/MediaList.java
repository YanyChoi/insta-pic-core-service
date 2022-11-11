package com.instantpic.coreservice.dto.media;

import java.util.ArrayList;
import java.util.List;

public class MediaList {

    private List<MediaDto> media;
    private int count;

    public MediaList() {
        media = new ArrayList<MediaDto>();
        count = 0;
    }

    public List<MediaDto> getMedia() {
        return media;
    }

    public void setMedia(List<MediaDto> media) {
        this.media = media;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
