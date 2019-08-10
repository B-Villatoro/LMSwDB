package com.smoothstack.lms.model;

public class Book {

    private String title;
    private int isbn;
    private int publisherId;
    private int authorId;

    public Book(String title, int isbn, int authorId,int publisherId) {
        this.title = title;
        this.isbn = isbn;
        this.publisherId = publisherId;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}