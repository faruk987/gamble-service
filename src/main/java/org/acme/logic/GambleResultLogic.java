package org.acme.logic;

import org.acme.models.Bet;

import javax.enterprise.context.ApplicationScoped;

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

    //Met message broker result toevoegen/afschrijven van saldo gebruiker
    public double calculatedWinLos(int home, int away, Bet bet){
        if (hasWon(home,away,bet)){
            return bet.getInlay() * bet.getQuotation();
        }else {
            return -bet.getInlay();
        }
    }

}
