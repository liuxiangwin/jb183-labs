package com.redhat.training.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.model.Item;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ItemService {

    @Inject
    private Logger log;

    @Inject
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
