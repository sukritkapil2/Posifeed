package com.posifeed.posifeed;

import androidx.annotation.Keep;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Keep
public class Article {
    public String heading;
    public String articleBrief;
    public String content;
    public String imageUrl;
    public String youtubeVid;
    public Date uploadDate;
    public String author;
    public String moreLink;
    public String articlePic;
    public Boolean isNew;
    private List<Map<String, String>> paras;
    private List<Map<String, String>> comments;

    public Article() {

    }

    public Article(String heading, String articleBrief, String content, String imageUrl, String youtubeVid, Date uploadDate, String author, String moreLink, String articlePic, Boolean isNew, List<Map<String, String>> paras, List<Map<String, String>> comments) {
        this.heading = heading;
        this.articleBrief = articleBrief;
        this.content = content;
        this.imageUrl = imageUrl;
        this.youtubeVid = youtubeVid;
        this.uploadDate = uploadDate;
        this.author = author;
        this.moreLink = moreLink;
        this.articlePic = articlePic;
        this.isNew = isNew;
        this.paras = paras;
        this.comments = comments;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getArticleBrief() {
        return articleBrief;
    }

    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYoutubeVid() {
        return youtubeVid;
    }

    public void setYoutubeVid(String youtubeVid) {
        this.youtubeVid = youtubeVid;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public List<Map<String, String>> getParas() {
        return paras;
    }

    public void setParas(List<Map<String, String>> paras) {
        this.paras = paras;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public void setComments(List<Map<String, String>> comments) {
        this.comments = comments;
    }
}
