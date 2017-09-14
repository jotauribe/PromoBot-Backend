package co.ia.promobot.interfaces.web.rest;

import co.ia.promobot.model.Location;
import co.ia.promobot.model.Promo;
import co.ia.promobot.services.FindBestPromoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("views")
public class ViewStatsRestService {

    @Inject
    private FindBestPromoService findBestPromoService;

    @POST
    @Path("promo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPromo(Location location){
        Promo bestPromo = findBestPromoService.execute(location);
        return Response.ok(bestPromo, MediaType.APPLICATION_JSON).build();
    }
}
