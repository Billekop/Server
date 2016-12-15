package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import com.google.gson.Gson;
import controllers.BookController;
import controllers.TokenController;
import model.User;
import Encrypters.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// The Java class will be hosted at the URI path "/Book"
/**
 * Endpoint klasse indeholder alle bog-endpoints. Klassen står for kald af metoder fra controller-klasse
 * og kommunikation med klient.
 */

@Path("/book")
public class BookEndpoint {

    BookController controller = new BookController();
    TokenController tokenController = new TokenController();

    public BookEndpoint() {
    }

    // The Java method will process HTTP GET requests

    /**
     * Denne metode gør det muligt for klienten at hente en liste over bøger.
     * Dataen, der bliver vist for klienten er krypteret jf. encryptDecryptXOR-metoden i Crypter-klassen
     * @return Metoden returner enten en status 200 eller 400, alt afhængig af om forespørgslen bliver godkendt
     * af serveren. Hertil følger også en fejlmeddelelse, hvis metoden returner en status 400.
     * @throws Exception
     */
    @GET
    @Produces("application/json")
    public Response get() throws Exception {



        if (controller.getBooks()!=null) {
            return Response
                    .status(200)
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(controller.getBooks()))))
                    //ændret
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Denne metode bruges til at hente data om en enkelt bog.
     * @param bookId metoden bruger bookId som parameter fra klienten, til at vide hvilken bog, der skal vises.
     * @return Ligesom tidligere metode returner denne også enten status 200 eller 400.
     * @throws Exception
     */
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Response get(@PathParam("id") int bookId) throws Exception {
        if (controller.getBook(bookId)!=null) {
            return Response
                    .status(200)
                    .entity((Crypter.encryptDecryptXOR(new Gson().toJson(controller.getBook(bookId)))))
                    //ændret
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Denne metode gør det muligt for klienten at ændre værdier på en bog (login er nødvendigt).
     * Det er en PUT-metode, hvilket betyder at den modtager et input fra klienten.
     * Metoden modtager også diverse parametre.
     * @param authToken dette parameter tjekker om klienten sender en access-token (om brugeren er verificeret).
     * @param id Tjekker id, for at vide hvilken bog, der skal ændres.
     * @param data modtager data om hvad der skal ændres.
     * @return Returner også status 200 eller 400 (med fejlbeskeder)
     * @throws Exception
     */
    @PUT
    @Path("/{bookId}")
    @Produces("application/json")

    public Response edit(@HeaderParam("authorization") String authToken, @PathParam("bookId") int id, String data) throws Exception {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){

            if (controller.getBook(id) != null) {
                String s = new Gson().fromJson(data,String.class);
                String decrypt = Crypter.encryptDecryptXOR(s);
                if (controller.editBook(id, decrypt)) {
                    return Response
                            .status(200)
                            .entity("{\"message\":\"Success! Book edited\"}")
                            .build();
                } else {
                    return Response
                            .status(400)
                            .entity("{\"message\":\"failed\"}")
                            .build();
                }
            } else {
                return Response
                        .status(400)
                        .entity("{\"message\":\"failed. Book not found\"}")
                        .build();
            }

        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();


    }

 /*  @POST
    @Produces("application/json")
    public Response create(String data) throws Exception {
        if (controller.addCurriculumBook(data)) {
            return Response
                    .status(200)
                    .entity("{\"message\":\"Success! Book created\"}")
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }
*/

    /**
     * Gør det muligt for klienten at slette eksisterende bøger
     * @param authToken Tjekker om klienten sender en access-token med request.
     * @param bookId Modtager ID om bog, der skal slettes
     * @return Returner status 200, hvis bog er slettet. Ellers status 400 med fejlbesked.
     * @throws Exception
     */
    @Path("/{id}")
    @DELETE
    public Response delete (@HeaderParam("authorization") String authToken, @PathParam("id") int bookId) throws Exception {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){

            if(controller.deleteBook(bookId)) {
                return Response.status(200).entity("{\"message\":\"Success! Book deleted\"}").build();
            }
            else return Response.status(400).entity("{\"message\":\"failed\"}").build();
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();


    }
}

