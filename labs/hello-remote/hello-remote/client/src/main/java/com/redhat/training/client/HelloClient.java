package com.redhat.training.client;

import com.redhat.training.ejb.HelloRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class HelloClient {

    public static void main(String[] args) throws Exception {

      //TODO Update JNDI address for the HelloBean
      String uriJNDI = "";

      try {
	          Context ic = new InitialContext();

            // Lookup the remote EJB
            HelloRemote hello = (HelloRemote) ic.lookup(uriJNDI);

            // Invoke the sayHello method
            System.out.println("Response from server = " + hello.sayHello("Shadowman"));

        } catch (NamingException ex) {

        	ex.printStackTrace();
        }


    }
}
