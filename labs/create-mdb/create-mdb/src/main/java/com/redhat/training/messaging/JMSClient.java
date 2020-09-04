package com.redhat.training.messaging;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Startup
@Singleton
public class JMSClient {

    @Resource(mappedName = "java:jboss/jms/queue/helloWorldQueue")
    private Queue helloWorldQueue;

    @Inject
    JMSContext context;

    public void sendMessage(String msg) {
        try {
            JMSProducer producer = context.createProducer();
            TextMessage message = context.createTextMessage(msg);
            producer.send(helloWorldQueue, message);
            System.out.println("Sent Message: "+ msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getMessage() {
    	
    	JMSConsumer consumer = context.createConsumer(helloWorldQueue);
    	try {
            TextMessage msg = (TextMessage) consumer.receiveNoWait();
            if(msg != null) {
	            System.out.println("Recieved Message: "+ msg);
	            return msg.getBody(String.class);
            }else {
            	return null;
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	consumer.close();
        }
    }
}
