package org.acme.controllers;

import com.google.gson.Gson;
import org.acme.logic.GambleResultLogic;
import org.acme.logic.StartGambleLogic;
import org.acme.models.Bet;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/gamble")
public class GambleController {
    @Inject
    StartGambleLogic startLogic;
    @Inject
    GambleResultLogic resultLogic;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String placeBet(@QueryParam("eventId") int eventId,
                           @QueryParam("prediction") int prediction,
                           @QueryParam("quatation") double quatation,
                           @QueryParam("inlay") int inlay) {

        Bet bet = new Bet(eventId,prediction,quatation,inlay);
        return startLogic.placeBet(4, bet);
    }

    @GET
    @Path("/result")
    @Produces(MediaType.TEXT_PLAIN)
    public double getResult(@QueryParam("eventId") int eventId,
                            @QueryParam("home") int home,
                           @QueryParam("away") int away){
        //hier bet ophalen van user
        Bet bet = new Bet(1,1,1.6,100);
        return resultLogic.calculatedWinLos(home,away,bet);
    }
}
