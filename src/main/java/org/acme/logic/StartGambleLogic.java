package org.acme.logic;

import com.google.gson.Gson;
import io.vertx.core.json.Json;
import org.acme.models.Bet;
import org.acme.models.UserWallet;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class StartGambleLogic {

   private static Bet currentBet;

    @Inject
    @Channel("ask-user-wallet")
    Emitter<String> userEmitter;
    public void placeBet(Bet bet) {
        currentBet = bet;
        userEmitter.send(bet.getUsername());
    }

    @Incoming("wallet")
    public void processResponse(Object result){
        String jsonvalue = Json.decodeValue((String) result).toString();
        UserWallet userWallet = Json.decodeValue(jsonvalue, UserWallet.class);

        if (userWallet.getWallet() >= 0 && userWallet.getWallet() > currentBet.getInlay()){
            //hier de bet aanmaken en opslaan
            //ook message sturen naar user om het inzet van zijn wallet af te halen
            System.out.println("Er kan gegokt worden");
        }
    }
}
