package com.example.fx2022;

public class BookResult {
    private int id;
    private String name;
    private String author;
    private int percentage;

    public BookResult(int id, String name, String author, int percentage){
        this.id = id;
        this.name = name;
        this.author = author;
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
