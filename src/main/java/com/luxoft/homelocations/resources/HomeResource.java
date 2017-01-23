package com.luxoft.homelocations.resources;

import com.luxoft.homelocations.dao.HomeDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.luxoft.homelocations.representations.Home;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response.Status;
import org.skife.jdbi.v2.DBI;

/**
 *
 * @author jjsanche
 */
@Path("/homes")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    private final HomeDAO homeDao;
    private final Validator validator;

    public HomeResource(DBI jdbi, Validator validator) {
        homeDao = jdbi.onDemand(HomeDAO.class);
        this.validator = validator;
    }

    @GET
    public Response getHomes() {
        List<Home> homes = homeDao.getHomes();
        return Response
                .ok(homes)
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response getHome(@PathParam("id") int id) {
        Home home = homeDao.getHomeById(id);
        return Response
                .ok(home)
                .build();
    }

    @POST
    public Response createHome(Home home) throws URISyntaxException {
        Set<ConstraintViolation<Home>> violations = validator.validate(home);

        if (violations.size() > 0) {
            List<String> validationMessages = new ArrayList<>();

            for (ConstraintViolation<Home> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(validationMessages)
                    .build();
        } else {
            int homeId = homeDao.createHome(home.getAddress1(), home.getAddress2(),
                    home.getCity(), home.getState(), home.getZipCode(), home.getCountry());
            return Response
                    .created(new URI(String.valueOf(homeId)))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHome(@PathParam("id") int id) {
        homeDao.deleteHome(id);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateHome(
            @PathParam("id") int id,
            Home home) {
        Set<ConstraintViolation<Home>> violations = validator.validate(home);

        if (violations.size() > 0) {
            List<String> validationMessages = new ArrayList<>();

            for (ConstraintViolation<Home> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(validationMessages)
                    .build();
        } else {
            homeDao.updateHome(id, home.getAddress1(), home.getAddress2(), home.getCity(), home.getState(), home.getZipCode(), home.getCountry());
            return Response
                    .ok(new Home(id, home.getAddress1(), home.getAddress2(), home.getCity(), home.getState(), home.getZipCode(), home.getCountry()))
                    .build();
        }
    }
}
