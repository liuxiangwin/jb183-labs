package com.redhat.training.todo.util;

import java.util.logging.Logger;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

public class Resources {

    //TODO add annotations for EntityManager
    private EntityManager em;

	//TODO add annotations for Logger
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
