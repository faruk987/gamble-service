package org.acme.logic;

import com.google.gson.Gson;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.Json;
import org.acme.models.Bet;
import org.acme.models.UserWallet;
import org.acme.service.BetEntityService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class StartGambleLogic {
    @Inject
    BetEntityService betEntityService;

   private static Bet currentBet;

    @Inject
    @Channel("ask-user-wallet")
    Emitter<String> userEmitter;
    public void placeBet(Bet bet) {
        currentBet = bet;
        userEmitter.send(bet.getUsername());
    }

    @Inject
    @Channel("send-inlay")
    Emitter<String> inlayEmitter;

    @Incoming("wallet")
    @Blocking
    @Transactional
    public void processResponse(Object result){
        String jsonvalue = Json.decodeValue((String) result).toString();
        UserWallet userWallet = Json.decodeValue(jsonvalue, UserWallet.class);

        if (userWallet.getWallet() > currentBet.getInlay()){
            userWallet.setWallet(currentBet.getInlay());

            String json = Json.encode(userWallet);
            inlayEmitter.send(json);

            betEntityService.storeBet(currentBet);
        }
    }
}
