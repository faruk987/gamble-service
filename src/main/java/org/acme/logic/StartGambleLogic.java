package org.acme.logic;

import com.google.gson.Gson;
import org.acme.models.Bet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StartGambleLogic {

    //Hier vanuit messagebroker opvragen hoeveel saldo de user heeft
    private boolean canGamble(double credit){
        return credit > 0;
    }

    public String placeBet(double credit, Bet bet){
        if (!canGamble(credit)){
            return "Not enough credit";
        }

        //opslaan db
        return new Gson().toJson(bet);
    }
}
