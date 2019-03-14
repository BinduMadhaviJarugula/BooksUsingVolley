package com.example.cse.volleybooksapi.model;

public class BookModel {
    public String author;
    public String booktitle;
    public String imageLink;
    public String desc;

    public String getAuthor() {
        return author;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDesc() {
        return desc;
    }

    public BookModel(String author, String booktitle, String imageLink, String desc) {
        this.author = author;
        this.booktitle = booktitle;
        this.imageLink = imageLink;
        this.desc = desc;
    }
}
