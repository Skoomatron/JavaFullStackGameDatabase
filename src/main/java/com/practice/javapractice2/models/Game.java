package com.practice.javapractice2.models;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
public class Game implements Serializable {
    @Id
    private String id;

    private String title;
    private String genre;

    public Game() {}

    public Game(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
