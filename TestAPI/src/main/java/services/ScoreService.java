package services;

import dao.DaoScore;
import entities.Score;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/scores")
public class ScoreService {

    private final DaoScore daoScore = new DaoScore();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllScores() {
        try {
            List<Score> scores = daoScore.getAllScores();
            return Response.ok(scores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/sort/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScoresByUser(@PathParam("id") int id_user) {
        try {
            List<Score> scores = daoScore.getSortedScoresByUser(id_user);
            return Response.ok(scores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
    @Path("/test/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSortedScoresByTest(@PathParam("id") int id_test) {
        try {
            List<Score> scores = daoScore.getSortedScoresByTest(id_test);
            return Response.ok(scores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addScore(Score score) {
        try {
            daoScore.addScore(score);
            return Response.status(Response.Status.CREATED).entity("Score added successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteScore(@PathParam("id") int id) {
        try {
            daoScore.deleteScore(id);
            return Response.ok("Score deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScoreById(@PathParam("id") int id) {
        try {
            Score score = daoScore.getScoreById(id);
            return Response.ok(score).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScoresByUserId(@PathParam("userId") int userId) {
        try {
            List<Score> scores = daoScore.getScoresByUserId(userId);
            return Response.ok(scores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateScore(@PathParam("id") int id, Score score) {
        try {
            daoScore.updateScore(score, id);
            return Response.ok("Score updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
}
