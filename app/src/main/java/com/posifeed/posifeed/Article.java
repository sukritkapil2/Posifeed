package com.posifeed.posifeed;

import androidx.annotation.Keep;

import java.util.Date;

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
    public String authorPic;
    public Boolean isNew;

    public Article() {

    }

    public Article(String heading, String content, String imageUrl, String youtubeVid,
                   String articleBrief, Date uploadDate, String author, String moreLink,
                   String authorPic, Boolean isNew) {
        this.heading = heading;
        this.isNew = isNew;
        this.content = content;
        this.authorPic = authorPic;
        this.imageUrl = imageUrl;
        this.youtubeVid = youtubeVid;
        this.articleBrief = articleBrief;
        this.uploadDate = uploadDate;
        this.author = author;
        this.moreLink = moreLink;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getArticleBrief() {
        return articleBrief;
    }

    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
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
}
