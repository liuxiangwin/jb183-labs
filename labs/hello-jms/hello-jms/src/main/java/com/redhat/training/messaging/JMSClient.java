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

    //TODO Map the destination queue to the admin object using JNDI and @Resource

    private Queue helloWorldQueue;

    //TODO Inject a JMSContext to get a Connection and Session to the embedded broker

    JMSContext context;

    public void sendMessage(String msg) {
        try {
            //TODO Create a JMSProducer
            JMSProducer producer = null;

            //TODO Create a TextMessage
            TextMessage message = null;

            //TODO Send the message


            System.out.println("Sent Message: "+ msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {

      //TODO Create a JMS Consumer
    	JMSConsumer consumer = null;

      try {
            //TODO receive a message without waiting
            TextMessage msg = null;
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
          //TODO close the consumer

        }
    }
}
