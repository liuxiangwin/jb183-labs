package com.redhat.training.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.model.Item;

@Stateless
public class ItemService {

    @Inject
    private Logger log;

    //TODO Inject an entity manager using the default persistence context

    @Inject
    private ItemRepository repository;

    public void register(Item item) throws Exception {
      log.info("Adding new task: " + item.getDescription());
      //TODO persist item with the entity manager
    }

    public void remove(Long id) {
  		//TODO lookup the item by its ID then remove it using the entity manager

  	}

    public void update(Item item) {
      //TODO update the item in the database using the entity maanger
    }
}
