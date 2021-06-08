package org.acme.logic;

import io.vertx.core.json.Json;
import org.acme.entity.BetEntity;
import org.acme.models.Bet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.models.BetResult;
import org.acme.models.PredictionType;
import org.acme.service.BetEntityService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class GambleResultLogic {
    @Inject
    BetEntityService betEntityService;

    private boolean hasWon(int home, int away, Bet bet){
        switch(bet.getPredictionType()) {
            case WIN:
                if (home > away) return true;
                break;
            case LOSE:
                if (home < away) return true;
                break;
            case DRAW:
                if (home == away) return true;
                break;
            default:
                throw new IllegalArgumentException("This prediction type is not accepted.");
        }

        return false;
    }

    @Inject
    @Channel("gamble-results")
    Emitter<String> priceEmitter;
    //Met message broker result toevoegen/afschrijven van saldo gebruiker
    public void calculatedWinLos(int home, int away, BetEntity bet){
        BetResult betResult = new BetResult(Math.toIntExact(bet.id),bet.getUsername(),0);
        if (hasWon(home,away,bet.getBet())){
            betResult.setResult((bet.getInlay() * bet.getQuotation()));
        }else {
            betResult.setResult(0);
        }

        String json = Json.encode(betResult);
        priceEmitter.send(json);
    }

}
