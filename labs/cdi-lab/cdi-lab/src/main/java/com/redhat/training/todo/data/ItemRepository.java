package com.redhat.training.todo.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;

//TODO make ItemRepository stateless
public class ItemRepository {

    //TODO add EntityManager annotations
    private EntityManager em;

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAllItemsForUser(User user) {

    	TypedQuery<Item> query =
                em.createQuery("SELECT i FROM Item i where i.user = :user" , Item.class);
    	query.setParameter("user",user);

        return query.getResultList();
    }
}
