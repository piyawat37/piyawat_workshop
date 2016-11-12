package com.example.evitected.workshop.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Evitected on 12/11/2559.
 */
public class News {
    @SerializedName("news_id")
    private String newsId;
    @SerializedName("title")
    private String title;
    @SerializedName("create_date")
    private String createDate;
    @SerializedName("short_description")
    private String shortDesc;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("detail")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
