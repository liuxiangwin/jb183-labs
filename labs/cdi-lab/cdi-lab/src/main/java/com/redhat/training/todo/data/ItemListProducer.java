package com.redhat.training.todo.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;

@RequestScoped
public class ItemListProducer {

    @Inject
    private ItemRepository itemRepository;
    
    @Inject
    private UserRepository userRepository;

    private List<Item> items;
    
    private User user;

    @Produces
    @Named
    public List<Item> getItems() {
        return items;
    }

    public void onItemListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Item item) {
        retrieveAllItems();
    }
    
    @PostConstruct
    public void init() {
    	user = userRepository.findById(1L);
    	retrieveAllItems();
    }

    public void retrieveAllItems() {
        items = itemRepository.findAllItemsForUser(user);
    }
}
