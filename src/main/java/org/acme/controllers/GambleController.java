package org.acme.controllers;

import com.google.gson.Gson;
import org.acme.entity.BetEntity;
import org.acme.logic.GambleResultLogic;
import org.acme.logic.StartGambleLogic;
import org.acme.models.Bet;
import org.mvel2.ast.ReturnNode;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Retention;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/gamble")
public class GambleController {
    @Inject
    JsonWebToken jwt;
    @Inject
    StartGambleLogic startLogic;
    @Inject
    GambleResultLogic resultLogic;

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        return jwt.getName();
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String placeBet(@QueryParam("eventId") int eventId,
                           @QueryParam("prediction") int prediction,
                           @QueryParam("quatation") double quatation,
                           @QueryParam("inlay") int inlay,
                           @QueryParam("user") String user){
        //hier controleren of user al op deze eventid heeft gegokt.

        Bet bet = new Bet(eventId,prediction,quatation,inlay,user);
        startLogic.placeBet(bet);

        return "Ok";
    }

    @GET
    @Path("/result")
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult(@QueryParam("id") int betId,
                            @QueryParam("home") int home,
                            @QueryParam("away") int away,
                            @QueryParam("user") String user){
        //hier bet ophalen van user
        BetEntity bet = BetEntity.findById(betId);
        if (bet != null) {
            resultLogic.calculatedWinLos(home,away,bet);
        }
        return "Ok";
    }

    @DELETE
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteAllBetsBySender(@QueryParam("user") String user) throws Exception {
        BetEntity.delete("sender", user);
    }
}
