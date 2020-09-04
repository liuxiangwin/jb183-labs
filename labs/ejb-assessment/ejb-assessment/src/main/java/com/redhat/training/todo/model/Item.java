package com.redhat.training.todo.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

    private Long id;

    private String description;

    private Boolean done = false;
    
    public Item(Long id, String description) {
    	this.id = id;
    	this.description = description;
    }
    
    public Item() {
    	
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Item item = (Item) o;

        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}