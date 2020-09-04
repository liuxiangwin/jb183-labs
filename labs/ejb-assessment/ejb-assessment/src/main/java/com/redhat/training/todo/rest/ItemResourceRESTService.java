package com.redhat.training.todo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.service.ItemService;

@Path("/items")
@RequestScoped
public class ItemResourceRESTService {

    // TODO: Inject the EJB
    ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> listAllItems() {
        return itemService.getAllItems();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item lookupItemById(@PathParam("id") long id) {
        Item item = itemService.find(id);
        if (item == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return item;
    }

    /**
     * Creates a new Item from the values provided. Will return a JAX-RS response with either 200 ok, or a bad request error,
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {

        Response.ResponseBuilder builder = null;

        try {

          if (item.getId() == null) {

            itemService.register(item);
            // Create an "ok" response
            builder = Response.ok();
          }
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    
}
