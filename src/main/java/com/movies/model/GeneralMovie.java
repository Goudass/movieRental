package com.movies.model;

import com.movies.model.schema.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralMovie extends Movie {
    private String title;
    private Date date;
    private String director;
    private String type;
    private final SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
    public GeneralMovie(Long id, String title, String date, String director, String type) {
        super.id = id;
        this.title = title;
        setDate(date);
        this.director = director;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateFormat() {
        return dt.format(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        try{
            this.date = dt.parse(date);
        } catch (ParseException e) {
            System.out.println("Parsing date error");
            this.date = null ;
        }

    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + getDateFormat() +
                ", director='" + director + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
