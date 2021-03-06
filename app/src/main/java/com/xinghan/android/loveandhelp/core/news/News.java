package com.xinghan.android.loveandhelp.core.news;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by xinghan on 3/23/15.
 */
public class News {
    private UUID mId;
    private String mDescription;
    private String mContent;
    private String mLink;
    @SerializedName(("title")) private String mTitle;
    @SerializedName("slug") private String mSlug;
    @SerializedName("photo") String mImage;
    @SerializedName("created") String mDate;
    @SerializedName("body") String mBody;

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

    public String getLink() {
        return mLink;
    }

    public String getImage() {
        return mImage;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }


    @Override
    public String toString() {
        return mTitle;
    }


}
