package org.acme.models;

public class Bet {
    private String username;
    private int eventId;
    private PredictionType predictionType;
    private double quotation;
    private int inlay;

    public Bet() {
    }

    public Bet(int eventId, int prediction, double quotation, int inlay, String username) {
        this.eventId = eventId;
        this.quotation = quotation;
        this.inlay = inlay;
        this.username = username;

        switch (prediction){
            case 1: this.predictionType = PredictionType.WIN;
            break;
            case 2: this.predictionType = PredictionType.LOSE;
            break;
            case 3: this.predictionType = PredictionType.DRAW;
            default:
                throw new IllegalArgumentException("Prediction type not accepted");
        }
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public PredictionType getPredictionType() {
        return predictionType;
    }

    public void setPredictionType(PredictionType predictionType) {
        this.predictionType = predictionType;
    }
}