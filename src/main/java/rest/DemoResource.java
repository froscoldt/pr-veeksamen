package rest;

import com.google.gson.Gson;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.PuSelector;
import utils.SetupTestUsers;

/**
 * @author lam@cphbusiness.dk
 */
@Path("availablecars")
public class DemoResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{week}/{address}")
    public String getAvailableCars(@PathParam("week") int weekNumber, @PathParam("address") String address) {
        return new Gson().toJson(SetupTestUsers.testSwappiFutureCalls(weekNumber, address));
    }

   

}
