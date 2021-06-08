package org.acme.service;

import org.acme.entity.BetEntity;
import org.acme.models.Bet;
import org.acme.models.PredictionType;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BetEntityService {
    public void storeBet(Bet bet){
        int prediction;

        switch (bet.getPredictionType()) {
            case WIN:
                prediction = 1;
                break;
            case DRAW:
                prediction = 3;
                break;
            case LOSE:
                prediction = 2;
                break;
            default:
                throw new IllegalArgumentException("Prediction type not accepted");
        }

        BetEntity betEntity = new BetEntity();
        betEntity.setUsername(bet.getUsername());
        betEntity.setEventId(bet.getEventId());
        betEntity.setInlay(bet.getInlay());
        betEntity.setPredictionType(prediction);
        betEntity.setQuotation(bet.getQuotation());
        betEntity.setResult(0);
        betEntity.persist();
    }

    public void updateBetResult(BetEntity betEntity,  double result){
        betEntity.setResult(result);
        betEntity.persist();
    }

    public void getBetByUserAndEvent(){

    }
}
