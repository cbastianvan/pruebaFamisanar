package com.dev.model.Child;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan.vanegas on 23/11/2017.
 */
public class Data {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Data{" +
                "url='" + url + '\'' +
                ", public_description='" + public_description + '\'' +
                ", title='" + title + '\'' +
                ", subreddit='" + advertiser_category + '\'' +
                ", icon_img='" + icon_img + '\'' +
                ", audience_target='" + audience_target + '\'' +
                '}';
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("public_description")
    @Expose
    private String public_description;

    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("advertiser_category")
    @Expose
    private String advertiser_category;

    @SerializedName("icon_img")
    @Expose
    private String icon_img;

    public String getThumbnail() {
        return icon_img;
    }

    public void setThumbnail(String thumbnail) {
        this.icon_img = thumbnail;
    }

    @SerializedName("audience_target")
    @Expose
    private String audience_target;

    public String getContest_mode() {
        return public_description;
    }

    public void setContest_mode(String contest_mode) {
        this.public_description = contest_mode;
    }

    public String getCategory() {
        return advertiser_category;
    }

    public void setCategory(String subreddit) {
        this.advertiser_category = subreddit;
    }

    public String getAudience() {
        return audience_target;
    }

    public void setAudience(String author) {
        this.audience_target = author;
    }

}
