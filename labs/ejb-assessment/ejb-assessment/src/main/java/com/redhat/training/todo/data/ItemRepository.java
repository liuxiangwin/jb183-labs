package com.redhat.training.todo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.training.todo.model.Item;

// This class simulates a fake database back-end for the 
// TODO List app

@ApplicationScoped
public class ItemRepository {
	
	@Inject
    private Logger log;
	
	private List<Item> myItemList;
    
    @PostConstruct
    public void seedTodoList()
    {
    	log.info("Seeding TODO List cache...");
    	myItemList = new ArrayList<Item>();
    	myItemList.add(new Item((long)1,"Buy Milk"));
    	myItemList.add(new Item((long)2,"Buy Eggs"));
    	myItemList.add(new Item((long)3,"Send EMail"));
    }
    
    public void addItem(Item item) {
    	myItemList.add(item);
    }
   
    
    public List<Item> getAllItems() {
    	if (myItemList == null) {
    		myItemList = new ArrayList<Item>();
    	}
    	return myItemList;
    }
    
    public Item getItem(Long id) {
    	Item testItem = null;
    	for (int i = 0; i < myItemList.size(); i++) {
    		if (myItemList.get(i).getId() == id) {
    			testItem =  myItemList.get(i);
    		}
    	}

    	return testItem;
    }

	public int getItemCount() {
		
		return myItemList.size();
	}
}
