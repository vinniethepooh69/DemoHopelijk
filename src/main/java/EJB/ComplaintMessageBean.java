package EJB;

import classes.ComplaintsEntity;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.Schedule;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MessageDriven(mappedName = "JNDI/MessageQeue", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector",
                propertyValue = "messageLength > 5"),
        @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue")
})public class ComplaintMessageBean implements MessageListener {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/DemoHopelijk/api/REST/check");

    int messageAmountPerTime = 0;
    public void onMessage(Message message1) {
        String response = "something went wrong";
        try {
            Response r = target.path("/badWords")
                    .request()
                    .post(Entity.json(message1.getBody(String.class)));
            response = r.readEntity(String.class);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        ComplaintsEntity message = new ComplaintsEntity();
        message.setStudent(1);

        message.setMessage(response);

        em.persist(message);
        em.flush();
        messageAmountPerTime  = messageAmountPerTime+1;

    }

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void logAmountOfComplaints() throws IOException {
        FileWriter fw = new FileWriter("C://Users//olivi/IdeaProjects/logger.txt",  true);
        PrintWriter out = new PrintWriter(fw);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        // Append the name the time and message amount per time to the file
        out.println("Amount of messages at: " + dtf.format(now) + " is: " + messageAmountPerTime);

        // Close the file.
        out.close();
        messageAmountPerTime = 0;
    }

}