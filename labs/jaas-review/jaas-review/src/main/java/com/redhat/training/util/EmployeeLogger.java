package com.redhat.training.util;

import java.io.FileWriter;
import java.io.IOException;

import javax.ejb.Stateless;

import com.redhat.training.model.Employee;

@Stateless
public class EmployeeLogger {
	
	public enum Operation {Create,Read,Update,Delete};
	
	/**
	 * Log an operation done on an Employee record
	 * @param employee Employee object that is being operated on
	 * @param operation Operation that is being done on the employee record
	 */
	public void logAction(Employee employee, Operation operation) {
		String message = operation.toString()+" Employee: "+employee;	
		writeMessageToFile(message);
	}
	
	/**
	 * Write a string to a log file
	 * @param message
	 */
	private void writeMessageToFile(String message) {
    	try
    	{
    		System.out.println("Logging: "+ message);
    	    String filename= "/home/student/JB183/labs/jaxrs-review/EmployeeLog.txt";
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
