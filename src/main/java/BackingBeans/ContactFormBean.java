package BackingBeans;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.*;
import jakarta.jms.Message;


@Named
@RequestScoped
public class ContactFormBean {
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
    private JMSContext context;
    @Resource(lookup = "JNDI/MessageQeue")
    private Destination printingQueu;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String writeMessage() {
        Message mes = context.createTextMessage(message);
        try {
            mes.setObjectProperty("messageLength", message.length());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        context.createProducer().send(printingQueu, mes);

        return "Home.xhtml";
    }
}
