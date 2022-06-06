package WebServices;

import jakarta.ws.rs.*;


import java.util.Arrays;


@Path("/REST/check")
public class RESTservice {
    public String[] badlist = {"darn","crud","damn"};

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/badWords")
    @Consumes("application/json")
    @Produces("application/json")
    public String checkMessage(String message) {

        String censored = message;
        int counter = 0;
        for ( String ss : badlist) {
            if(message.toLowerCase().contains(ss)){
                char[] charArray = new char[ss.length()];
                Arrays.fill(charArray, '*');
                String censor = new String(charArray);
                censored = censored.toLowerCase().replace(ss,censor);
            };
            counter = counter + 1;
        }
        return censored;
    }

}
