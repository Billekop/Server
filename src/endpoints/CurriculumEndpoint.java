package endpoints;

import com.google.gson.Gson;
import controllers.CurriculumController;
import controllers.TokenController;
import Encrypters.*;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


/**
 * Created by magnusrasmussen on 17/10/2016.
 */
//implements IEndpoints HUSK AT ÆNDRE INTERFACET VED PUT
@Path("/curriculum")
public class CurriculumEndpoint {
    CurriculumController curriculumController;

    TokenController tokenController = new TokenController();


    public CurriculumEndpoint(){

        curriculumController = new CurriculumController();
    }


    /**
     * Metode til at hente alle bøgerne på et semester
     * @param curriculumID
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Path("/{curriculumID}/books")
    @Produces("application/json")
    public Response getCurriculumBooks(@PathParam("curriculumID") int curriculumID) throws IllegalAccessException {

        if (curriculumController.getCurriculum(curriculumID) != null) {
            return Response
                    .status(200)
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(curriculumController.getCurriculumBooks(curriculumID)))))
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                //error response
                .status(400)
                .entity("{\"message\":\"failed\"}")
                .build();
    }
    }

    /**
     * Metode til at hente alle semestre
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Produces("application/json")
    public Response get() throws IllegalAccessException {

        if (curriculumController.getCurriculums() != null) {
            return Response
                    .status(200)
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(curriculumController.getCurriculums()))))
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                    //error response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Metode til at hente et bestemt semester
     * @param id
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Path("/{curriculumID}")
    @Produces("application/json")
    public Response get(@PathParam("curriculumID") int id) throws IllegalAccessException {


        if (curriculumController.getCurriculums() != null) {
            return Response
                    .status(200)
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(curriculumController.getCurriculum(id)))))
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                    //error response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Metode til at oprette nyt semester
     * @param data
     * @return
     * @throws Exception
     */

    @POST
    @Produces("application/json")
    public Response create(String data) throws Exception {
        String s = new Gson().fromJson(data,String.class);
        String decrypt = Crypter.encryptDecryptXOR(s);
        if (curriculumController.addCurriculum(decrypt)) {
            //demo to check if it returns this on post.
            return Response
                    .status(200)
                    //nedenstående skal formentlig laves om. Den skal ikke returne curriculums. Lavet for at checke
                    //at den skriver til db.
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(curriculumController.getCurriculums()))))
                    .build();
        }
        else return Response
                .status(400)
                .entity("{\"message\":\"Failed.\"}")
                .build();
    }

    @POST
    @Path("/{curriculumID}/book")
    @Produces("application/json")

    public Response create(@HeaderParam("authorization") String authToken, @PathParam("curriculumID")int curriculumID, String data) throws Exception {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            String s = new Gson().fromJson(data,String.class);
            String decrypt = Crypter.encryptDecryptXOR(s);
            if (curriculumController.addCurriculumBook(curriculumID, decrypt)) {
                return Response
                        .status(200)
                        .entity("Success!")
                        .build();
            }
            else {
                return Response
                        .status(400)
                        //nedenstående skal formentlig laves om. Den skal ikke returne curriculums. Lavet for at checke
                        //at den skriver til db.
                        .build();
            }

        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();
    }
    /**
     * Metode til at ændre et semester
     * @return
     */

    @PUT
    @Path("/{curriculumID}")
    @Produces("application/json")

    public Response edit(@HeaderParam("authorization") String authToken, @PathParam("curriculumID") int id, String data) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            if (curriculumController.getCurriculum(id)!=null) {
                String s = new Gson().fromJson(data,String.class);
                String decrypt = Crypter.encryptDecryptXOR(s);
                if (curriculumController.editCurriculum(id, decrypt)) {
                    return Response
                            .status(200)
                            .entity("{\"message\":\"Success! Curriculum was changed.\"}")
                            .build();
                }
                else {
                    return Response
                            .status(400)
                            .entity("{\"message\":\"failed\"}")
                            .build();
                }

            }
            else {
                return Response
                        .status(400)
                        .entity("{\"message\":\"failed. Curriculum doesn't exist.\"}")
                        .build();
            }
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();

    }


    /**
     * Metode til at slette et semester
     * @param id
     * @return
     * @throws SQLException
     */
    @DELETE
    @Path("/{curriculumId}")
    @Produces("application/json")

    public Response delete(@HeaderParam("authorization") String authToken, @PathParam("curriculumId") int id) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            if(curriculumController.deleteCurriculum(id)) {
                return Response.status(200).entity("{\"message\":\"Curriculum was deleted\"}").build();
            }
            else return Response.status(400).entity("{\"message\":\"Failed. Curriculum was not deleted\"}").build();
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();
    }
}
