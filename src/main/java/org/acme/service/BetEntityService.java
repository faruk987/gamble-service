package org.acme.service;

import org.acme.entity.BetEntity;
import org.acme.logic.GambleResultLogic;
import org.acme.models.Bet;
import org.acme.models.PredictionType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Random;

@ApplicationScoped
public class BetEntityService {
    @Inject
    GambleResultLogic resultLogic;

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
        betEntity.setCreatedon(LocalDateTime.now());
        betEntity.setResult(0);
        betEntity.persist();

        Random home = new Random();
        Random away = new Random();
        resultLogic.calculatedWinLos(1,0,betEntity);
    }

    public void updateBetResult(long id,  double result){
        System.out.println(id);
        BetEntity betEntity = BetEntity.findById(id);
        System.out.println(betEntity.getUsername());;
        betEntity.setResult(result);
        betEntity.persist();
    }
}
