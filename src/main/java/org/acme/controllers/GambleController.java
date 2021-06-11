package org.acme.controllers;

import com.google.gson.Gson;
import org.acme.entity.BetEntity;
import org.acme.logic.GambleResultLogic;
import org.acme.logic.StartGambleLogic;
import org.acme.models.Bet;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gamble")
public class GambleController {

    @Inject
    StartGambleLogic startLogic;
    @Inject
    GambleResultLogic resultLogic;

/*    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        return jwt.getName();
    }*/


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response.Status placeBet(@QueryParam("eventId") int eventId,
                                    @QueryParam("prediction") int prediction,
                                    @QueryParam("quotation") double quatation,
                                    @QueryParam("inlay") int inlay,
                                    @QueryParam("user") String user){

        Bet bet = new Bet(eventId,prediction,quatation,inlay,user);
        startLogic.placeBet(bet);


        return Response.Status.ACCEPTED;
    }

    @GET
    @Path("/result")
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult(@QueryParam("id") int betId,
                            @QueryParam("home") int home,
                            @QueryParam("away") int away){
        //hier bet ophalen van user
        BetEntity bet = BetEntity.findById((long)betId);
        if (bet != null) {
            resultLogic.calculatedWinLos(home,away,bet);

            return new Gson().toJson(bet);
        }
        return null;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll(@QueryParam("user") String user){
        List<BetEntity> betEntities = BetEntity.list("username", user);
        String json = new Gson().toJson(betEntities);

        return json;
    }

    @DELETE
    @Path("/all")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteAllBetsBySender(@QueryParam("user") String user) {
        BetEntity.delete("sender", user);
    }
}
