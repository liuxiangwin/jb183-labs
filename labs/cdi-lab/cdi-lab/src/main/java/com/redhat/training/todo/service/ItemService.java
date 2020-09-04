package com.redhat.training.todo.service;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.model.Item;


//TODO make ItemService stateless
public class ItemService {

    //TODO add annotation for logger
    private Logger log;

    //TODO add annotation for entitymanager
    private EntityManager em;

    @Inject
    private ItemRepository repository;

    @Inject
    private Event<Item> itemEventSrc;

    public void register(Item item) throws Exception {
      log.info("Adding new task: " + item.getDescription());
      em.persist(item);
      itemEventSrc.fire(item);
    }

    public void remove(Long id) {
  		em.remove(repository.findById(id));
  	}

    public void update(Item item) {
      em.merge(item);
    }
}
