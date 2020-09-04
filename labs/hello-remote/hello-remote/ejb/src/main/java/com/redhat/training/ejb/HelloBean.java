package com.redhat.training.ejb;


import javax.ejb.Stateless;


@Stateless
public class HelloBean implements HelloRemote {

    public String sayHello(String name) {

    // respond back with "Hello, {name}".
    return "Hello, " + name;
    }
}
