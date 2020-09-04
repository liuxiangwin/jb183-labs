package com.redhat.training.todo.messaging;

import java.io.FileWriter;
import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>
 * A simple Message Driven Bean that asynchronously processes the messages sent to the queue.
 * </p>
 *
 *
 */
//TODO Add annotations to register this class as an MDB to listen on the TodoListQueue

public class QueueListener { //TODO implement the MessageListener interface

    //TODO add the onMessage method


    public void writeMessageToFile(String message) {
    	try
    	{
    	    String filename= "/home/student/JB183/labs/messaging-lab/ItemStatusLog.txt";
    	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
    	    fw.write(message);
    	    fw.write("\n");//appends the string to the file
    	    fw.close();
    	}
    	catch(IOException ioe)
    	{
    	    System.err.println("IOException: " + ioe.getMessage());
    	}

    }
}
