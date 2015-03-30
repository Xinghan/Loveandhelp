package com.xinghan.android.loveandhelp;

import java.util.UUID;

/**
 * Created by xinghan on 3/23/15.
 */
public class News {
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getContent() {
        return mContent;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private String mContent;
}
