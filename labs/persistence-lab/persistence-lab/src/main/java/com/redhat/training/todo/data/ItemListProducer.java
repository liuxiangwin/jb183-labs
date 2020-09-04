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

@RequestScoped
public class ItemListProducer {

    @Inject
    private ItemRepository itemRepository;

    private List<Item> items;

    @Produces
    @Named
    public List<Item> getItems() {
        return items;
    }

    public void onItemListChanged(final Item item) {
        retrieveAllItems();
    }

    @PostConstruct
    public void init() {
    	retrieveAllItems();
    }

    public void retrieveAllItems() {
        items = itemRepository.findAllItems();
    }
}
