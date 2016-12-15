package endpoints; /**
 * Created by madsgade on 17/10/2016.
 */

import Encrypters.*;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Denne klasse modtager krypteret kommunikation fra klienten, og dekrypterer det vha. Crypter-klassen,
 * så resten af serveren kan gøre brug af dataen.
 */
@Path("/crypter")
public class CrypterEndpoint {
    Gson gson;
    Crypter crypter;

    public CrypterEndpoint() {
        gson = new Gson();
        crypter = new Crypter();
    }

    /**
     * Modtager krypteret data
     * @return returnerer dekrypteret data
     */
    @GET
    @Produces("text/plain")

    public String xor() {
        return Crypter.xor();
    }


    @Path("/getit")
    @GET
    @Produces("text/plain")
    public String getit() {
        return "Got it!";
    }
}