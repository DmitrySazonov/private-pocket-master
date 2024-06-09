package com.privatepocket.privatepocket.web;

import java.time.LocalDateTime;


public class UrlRecordDTO {
    private String repository;
    private String url;
    private String title;
    private LocalDateTime createDate;

    public UrlRecordDTO(String repository, String url, String title, LocalDateTime createDate) {
        this.repository = repository;
        this.url = url;
        this.title = title;
        this.createDate = createDate;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
