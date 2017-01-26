package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import Encrypters.*;
import com.google.gson.Gson;
import controllers.TokenController;
import controllers.UserController;
import model.User;
import model.UserLogin;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
// Iendpoints klassen vil hostes på URI path "/users"
// implements IEndpoints The Java class will be hosted at the URI path "/users"
@Path("/user")
public class UsersEndpoint  {
    UserController controller = new UserController();
    TokenController tokenController = new TokenController();

    public UsersEndpoint() {
    }



@Path("/create")
@POST
@Produces("application/json")
public Response create(String newUserData) throws Exception {
    if(!newUserData.equals("")){
        if (controller.addUser(newUserData)) {
            //test om den returnerer dette på post.
            return Response
                    .status(200)
                    .entity("{\"Status:\":\"Din bruger er nu tilføjet\"}")
                    .build();
        }
        else
            return Response.status(400).entity("{\"Status:\":\"Det lykkedes ikke at oprette en bruger\"}").build();
    }
    else
        return Response.status(400).entity("{\"message\":\"Det lykkedes ikke at oprette en bruger\"}").build();
}

 @Path("/{id}")
 @DELETE
 public Response delete (@HeaderParam("authorization") String authToken, @PathParam("id") int userId) throws SQLException {

     User user = tokenController.getUserFromTokens(authToken);

     if (user != null){
         if(controller.deleteUser(userId)) {
             return Response.status(200).entity("{\"Status:\":\"Din bruger er nu slettet. Fortsat god dag \"}").build();
         }
         else return Response.status(400).entity("{\"Status:\":\"Vi kunne desværre ikke slette din bruger\"}").build();
     }else return Response.status(400).entity("{\"Status\":\"Vi kunne desværre ikke slette din bruger\"}").build();


 }



    /**
     * Metode til login.
     *
     * @param data
     * @return
     * @throws SQLException
     */
@POST
@Path("/login")
@Produces("application/json")
public Response login(String data) throws SQLException {


    UserLogin userLogin = new Gson().fromJson(data, UserLogin.class);

    String token = tokenController.authenticate(userLogin.getUsername(), Digester.hashWithSalt(userLogin.getPassword()));

    if (token != null) {
        //tjek om den returnerer dette på post.
        return Response
                .status(200)
                .entity(new Gson().toJson(token))
                .build();
    } else return Response
            .status(401)
            .build();
}

    //parth getuser. Når denne path rammes, bliver der først lavet et response, derefter henter den
    // token som string, fra getuserfromToken. sætter user lig med token fra tokencontroller.

    @POST
    @Path("/getuser")
    @Produces("application/json")
    public Response getUserFromToken(String token) throws SQLException {
        token = token.replaceAll("\"", "");
        User user = tokenController.getUserFromTokens(token);
        if (token != null) {
            //demo til tjek om den returerner på denne post
            return Response
                    .status(200)
                    .entity(new Gson().toJson(user))
                    .build();
        } else return Response
                .status(401)
                .build();
    }

    /**
     * Metode til at hente alle user
     *
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/getallusers")
    @Produces("application/json")
    public Response getAllUsers() throws SQLException {
        ArrayList<User> u = controller.getUsers();

        if (u != null) {
            //demo to check if it returns this on post.
            return Response
                    .status(200)
                    .entity(new Gson().toJson(u))
                    .build();
        } else return Response
                .status(401)
                .build();
    }


    /**
     *Metode til at opdatere brugeren som er logget ind.
     *
     *
     * @param data
     * @return
     * @throws SQLException
     */
    @Path("/updateuser")
    @Produces("application/json")
    @POST
    //modificeret. Rammes med informationen "data". Derefter laves et datasplit i 5 hashes.
    public Response updateUser( String data) throws SQLException {
        String[] dataanduserid = data.split("#####");
        int userId = Integer.parseInt(dataanduserid[0]);
        data = dataanduserid[1];
        System.out.println("For userID and data:" + userId+"//" + data);
        boolean ret = tokenController.updateUser(userId, data);


        if (ret){
            return Response
                    .status(400)
                    .entity("{\"message\":\"Der er nu blevet opdateret information for: Userid\":"+userId+"}")
                    .build();

        } else return Response
                .status(400)
                .entity("{\"message\":\"failed\"}")
                .build();
    }

    /**
     * Method to handle the log out functionality
     *
     * @param token
     * @return
     * @throws SQLException
     */
    @POST //dette er den nye logout. sat ind.
    @Path("/logout")
    public Response logout (String token) throws SQLException {
        token = token.replaceAll("\"", ""); //Det token som sendes til url indeholder en ". Det bliver fjernet her, så det passer med det token i DB.
        if(tokenController.deleteToken(token)) {

            return Response
                    .status(200)
                    .entity("Du er logget ud.")
                    .build();

        } else return Response
                .status(400)
                .entity("Du blev desværre ikke logget ud")
                .build();
    }
    /**
     * Metode til at slette bruger samt token.
     * @param token
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/deleteuser")
    public Response deleteUser (String token) throws SQLException {
        token = token.replaceAll("\"", ""); //Det token som sendes til url indeholder en ". Det bliver fjernet her, så det passer med det token i DB.
        User user = tokenController.getUserFromTokens(token);
        if (user != null){
            if(controller.deleteUser(user.getUserID())) {
                tokenController.deleteToken(token);
                return Response.status(200).entity("{\"Status\":\"Din bruger er nu slettet-server\"}").build();
            }
            else
                return Response.status(400).entity("{\"Status\":\"Din bruger blev desværre ikke slettet\"}").build();
        }else
            return Response.status(400).entity("{\"Status\":\"Din bruger blev desværre ikke slettet\"}").build();

    }

}