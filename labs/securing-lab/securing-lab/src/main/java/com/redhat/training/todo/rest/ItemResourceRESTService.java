package com.redhat.training.todo.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.data.UserRepository;
import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;
import com.redhat.training.todo.service.ItemService;
import com.redhat.training.todo.service.UserService;

@Path("/items")
@RequestScoped
public class ItemResourceRESTService {

	@Inject
	private Logger log;

	@Context
	private HttpServletRequest request;

	private User currentUser;

    @Inject
    private ItemRepository repository;

    @Inject
    private UserRepository userRepo;

    @Inject
    ItemService itemService;

    @Inject
    private UserService userService;


    @PostConstruct
    public void setUser() {
    	Principal principal = request.getUserPrincipal();
    	log.info("Current user is: "+ principal.getName());
        // get login from principal
        String login = principal.getName();
        //get all informations of user from EJB : UserFacade

    	currentUser = userRepo.findByName(login);

    	if(currentUser == null) {
    		currentUser = new User();
    		currentUser.setUsername(login);
    		userService.addUser(currentUser);

    	}
    }

	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> listAllItems() {
        return repository.findAllItemsForUser(currentUser);
    }


    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item lookupItemById(@PathParam("id") long id) {
        Item item = repository.findById(id);
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

    	item.setUser(currentUser);

        Response.ResponseBuilder builder = null;

        try {

          if (item.getId() == null) {

            itemService.register(item);
            // Create an "ok" response
            builder = Response.ok();
          }
          else {
            Item itemToUpdate = lookupItemById(item.getId());
            itemToUpdate.setDescription(item.getDescription());
            itemToUpdate.setDone(item.isDone());
            itemService.update(itemToUpdate);
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

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteItem(@PathParam("id") Long id) {
        itemService.remove(id);
    }
}
