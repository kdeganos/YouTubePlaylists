package com.epicodus.youtubeplaylists.models;

/**
 * Created by Guest on 7/7/16.
 */
public class VideoObj {
    String mVideoId;
    String mPublishedAt;
    String mTitle;
    String mDescription;
    String mThumbnail;

    public VideoObj() {
    }

    public VideoObj(String videoId, String publishedAt, String title, String description, String thumbnail) {
        this.mVideoId = videoId;
        this.mPublishedAt = publishedAt;
        this.mTitle = title;
        this.mDescription = description;
        this.mThumbnail = thumbnail;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getThumbnail() {
        return mThumbnail;
    }
}
