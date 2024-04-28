package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import entities.Utilisateur;
import dao.DaoUtilisateur;

@Path("/users")
public class UtilisateurService {

	private final DaoUtilisateur daoUtilisateur;

    public UtilisateurService() {
        daoUtilisateur = new DaoUtilisateur();
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ServiceHelloWorld() {
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        try {
            List<Utilisateur> users = daoUtilisateur.getAllUsers();
            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        try {
            Utilisateur user = daoUtilisateur.getUserById(id);
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Utilisateur user) {
        try {
            daoUtilisateur.addUser(user);
            return Response.status(Response.Status.CREATED).entity("User added successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteUser(@PathParam("id") int id) {
        try {
            daoUtilisateur.deleteUser(id);
            return Response.ok("User deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        try {
            //DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
        	//System.out.println("API LOGIN :"+email +" : "+password);
            Utilisateur user = daoUtilisateur.login(email, password);
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
    
    @Path("email/{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@PathParam("email") String email) {
        try {
            //DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
            Utilisateur user = daoUtilisateur.getUserByEmail(email);
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, Utilisateur user) {
        try {
            DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
            // Mettre à jour l'utilisateur avec l'ID spécifié
            user.setId(id);
            daoUtilisateur.updateUser(id, user);
            return Response.ok("User updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }


}
