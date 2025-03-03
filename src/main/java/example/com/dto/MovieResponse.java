package example.com.dto;

import java.io.Serializable;

public class MovieResponse implements Serializable {
    private String title;
    private int playTime;

    public MovieResponse(String title, int length) {
        this.title = title;
        this.playTime = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
