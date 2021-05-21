package org.acme.logic;

import io.vertx.core.json.Json;
import org.acme.models.Bet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.models.BetResult;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class GambleResultLogic {
    private final int WIN = 1;
    private final int LOSE = 2;
    private final int DRAW = 3;

    private boolean hasWon(int home, int away, Bet bet){
        if (home > away && bet.getPrediction() == WIN){
            return true;
        }else if (home < away && bet.getPrediction() == LOSE){
            return true;
        }else if (home == away && bet.getPrediction() == DRAW){
            return true;
        }else {
            return false;
        }
    }

    @Inject
    @Channel("gamble-results")
    Emitter<String> priceEmitter;
    //Met message broker result toevoegen/afschrijven van saldo gebruiker
    public void calculatedWinLos(int home, int away, Bet bet){
        BetResult betResult = new BetResult(1,"user",0);
        if (hasWon(home,away,bet)){
            betResult.setResult((bet.getInlay() * bet.getQuotation()));
            String json = Json.encode(betResult);
            priceEmitter.send(json);
        }else {
            betResult.setResult(0);
            String json = Json.encode(betResult);
            priceEmitter.send(json);
        }
    }

}
