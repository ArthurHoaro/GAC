package listener;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import remote.FMessageServicesRemote;

import model.Employee;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
        propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(
        propertyName = "destination", propertyValue = "queue/test") })

public class QueueListenerMDB implements MessageListener {
	
	
	@EJB
	FMessageServicesRemote fes;
    public QueueListenerMDB() {
    }
 
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println("Queue: I received a TextMessage at "
                                + new Date());
                TextMessage msg = (TextMessage) message;
                System.out.println("Message is : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                System.out.println("Queue: I received an ObjectMessage at "
                                + new Date());
                	ObjectMessage msg= (ObjectMessage)message;
                	if(msg.getObject() instanceof model.Message){
	                	model.Message m = (model.Message)msg.getObject();                  	
	                	fes.addItem(m);    
	                	System.out.println("message content");
	                	System.out.println(m.getContent());
                	}
            
            } else {
                System.out.println("Not a valid message for this Queue MDB");
            }
 
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}