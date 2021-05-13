package org.acme.models;

public class Bet {
    int eventId;
    int home;
    int away;
    double quotation;
    int inlay;

    public Bet(int eventId, int home, int away, double quotation, int inlay) {
        this.eventId = eventId;
        this.home = home;
        this.away = away;
        this.quotation = quotation;
        this.inlay = inlay;
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getHome() {
        return home;
    }
    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }
    public void setAway(int away) {
        this.away = away;
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
}