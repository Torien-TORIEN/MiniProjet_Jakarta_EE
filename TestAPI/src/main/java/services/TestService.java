package services;

import dao.DaoTest;
import entities.Question;
import entities.Test;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tests")
public class TestService {

    private final DaoTest daoTest = new DaoTest();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTests() {
        try {
            List<Test> tests = daoTest.getAllTests();
            return Response.ok(tests).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestById(@PathParam("id") int id) {
        try {
            Test test = daoTest.getTestById(id);
            return Response.ok(test).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTest(Test test) {
        try {
            daoTest.addTest(test);
            return Response.status(Response.Status.CREATED).entity("Test added successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteTest(@PathParam("id") int id) {
        try {
            daoTest.delete(id);
            return Response.ok("Test deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTest(@PathParam("id") int id, Test test) {
        try {
            daoTest.updateTest(id, test);
            return Response.ok("Test updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
    @Path("/{id}/questions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionsByCategory(@PathParam("id") int id) {
        try {
            Test test = daoTest.getTestById(id);
            if (test != null) {
                List<Question> questions = daoTest.getQuestionByCategory(test.getCategorie());
                return Response.ok(questions).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Test not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}/randomQuestions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomQuestionsByCategory(@PathParam("id") int id, @QueryParam("count") int count) {
        try {
            Test test = daoTest.getTestById(id);
            if (test != null) {
                List<Question> questions = daoTest.getRandomQuestionByCategorie(test.getCategorie(), count);
                return Response.ok(questions).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Test not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
}
