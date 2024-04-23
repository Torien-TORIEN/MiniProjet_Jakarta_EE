package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import dao.DaoQuestion;
import entities.Question;

@Path("/questions")
public class QuestionService {

    private final DaoQuestion daoQuestion;

    public QuestionService() {
        this.daoQuestion = new DaoQuestion();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuestions() {
        try {
            List<Question> questions = daoQuestion.getAllQuestions();
            return Response.ok(questions).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionById(@PathParam("id") int id) {
        try {
            Question question = daoQuestion.getQuestionById(id);
            return Response.ok(question).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addQuestion(Question question, @QueryParam("id_test") int idTest) {
        try {
            daoQuestion.addQuestion(question, idTest);
            return Response.status(Response.Status.CREATED).entity("Question added successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }


    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateQuestion(@PathParam("id") int id, Question question) {
        try {
            daoQuestion.updateQuestion(question, id);
            return Response.ok("Question updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteQuestion(@PathParam("id") int id) {
        try {
            daoQuestion.deleteQuestion(id);
            return Response.ok("Question deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomQuestions(@QueryParam("nb") int nb) {
        try {
            List<Question> randomQuestions = daoQuestion.getRandomQuestion(nb);
            return Response.ok(randomQuestions).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }


}
