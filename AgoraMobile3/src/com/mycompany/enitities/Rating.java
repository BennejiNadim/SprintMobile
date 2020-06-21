/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enitities;

/**
 *
 * @author ASUS
 */
public class Rating {
    private int id;
    private String feedback;
    private int ratings;

    public Rating() {
    }

    public Rating(String feedback, int ratings) {
        this.feedback = feedback;
        this.ratings = ratings;
    }

    public Rating(int id, String feedback, int ratings) {
        this.id = id;
        this.feedback = feedback;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRatings() {
        return ratings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
    
}
