package EJB;

import classes.BookInformation;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;

import java.util.ArrayList;

@Stateless
@WebService(portName = "awardChecker", serviceName = "awardService")
@SOAPBinding(style= SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public class SOAPService {
    public boolean awardBoolean;
    ArrayList<String> pulitzerBooks = new ArrayList<String>();
    @WebMethod
    public boolean awardWon(BookInformation bookInfo){
        pulitzerBooks.add("teststory");
        if(pulitzerBooks.contains(bookInfo.title_of_Book))
            awardBoolean = true;
        else{
            awardBoolean = false;
        }
        return awardBoolean;
    }

    public static void main(String[] args){
        Endpoint.publish("https://localhost:8080/awardWinner", new SOAPService());
    }

}
/*access by:
@WebServiceRef
of
@Inject
private SOAPService awardService;

    public boolean awardWon(BookInformation bookInformation) {
        SOAPService soapService = awardService.getSOAPServicePort(); ?
        return soapService.awardWon(bookInformation);
    }
 */