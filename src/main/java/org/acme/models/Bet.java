package org.acme.models;

public class Bet {
    private String username;
    private int eventId;
    private int prediction;
    private double quotation;
    private int inlay;

    public Bet() {
    }

    public Bet(int eventId, int prediction, double quotation, int inlay, String username) {
        this.eventId = eventId;
        this.prediction = prediction;
        this.quotation = quotation;
        this.inlay = inlay;
        this.username = username;
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPrediction() {
        return prediction;
    }
    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public double getQuotation() {
        return quotation;
    }
    public void setQuotation(double quotation) {
        this.quotation = quotation;
    }

    public int getInlay() {
        return inlay;
    }
    public void setInlay(int inlay) {
        this.inlay = inlay;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}