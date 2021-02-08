package com.essentials.springweb.web.model;

public class Book {
    private long id;
    private String name;
    private String author;

    public Book(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Book setId (long id) {
        this.id = id;
        return this;
    }

    public long getId () {
        return this.id;
    }

    public Book setName (String name) {
        this.name = name;
        return this;
    }

    public String getName () {
        return this.name;
    }

    public Book setAuthor (String author) {
        this.author = author;
        return this;
    }

    public String getAuthor () {
        return this.author;
    }
    
}
