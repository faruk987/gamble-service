package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.acme.models.Bet;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BetEntity extends PanacheEntity {
    private String username;
    private int eventId;
    private int predictionType;
    private double quotation;
    private int inlay;
    private double result;
    private LocalDateTime createdon;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPredictionType() {
        return predictionType;
    }
    public void setPredictionType(int predictionType) {
        this.predictionType = predictionType;
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

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }
    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public Bet getBet(){
        return new Bet(eventId, predictionType,quotation,inlay,username);
    }

    public static BetEntity findByUserAndEventId(String username, int eventId){
        List<BetEntity> betEntities = list("username", username);

        BetEntity result = null;
        for (BetEntity c : betEntities) {
            if (eventId == c.getEventId()) {
                result = c;
                break;
            }
        }

        return result;
    }

  /*  public static List<BetEntity> findByName(String username){
        return (List<BetEntity>) find("username", username);
    }*/
}
