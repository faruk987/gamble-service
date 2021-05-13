package org.acme.controllers;

import com.google.gson.Gson;
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
    StartGambleLogic logic;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String placeBet(@QueryParam("eventId") int eventId,
                           @QueryParam("home") int home,
                           @QueryParam("away") int away,
                           @QueryParam("quatation") double quatation,
                           @QueryParam("inlay") int inlay) {

        Bet bet = new Bet(eventId,home,away,quatation,inlay);
        return logic.placeBet(4, bet);
    }
}
