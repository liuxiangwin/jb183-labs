package com.redhat.training.todo.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redhat.training.todo.model.Item;

@ApplicationScoped
public class ItemRepository {

    //TODO Inject EntityManager using the default persistence context


    public Item findById(Long id) {
      //TODO use the entity manager to implement the findById method
      return null;
    }

    public List<Item> findAllItems() {
      //TODO Create a query to select all the items in order by whether or not they are done
    	TypedQuery<Item> query = null;

      return query.getResultList();
    }
}
