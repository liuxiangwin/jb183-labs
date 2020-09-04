package com.redhat.training.todo.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
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

// *Add the path annotation*
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
			String login = "";
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			// get login from principal
			login = principal.getName();
		} else {
			login = "Guest";
		}
		currentUser = userRepo.findByName(login);
			// If it's a new user, add them to the database
    	if(currentUser == null) {
    		currentUser = new User();
    		currentUser.setUsername(login);
    		userService.addUser(currentUser);

    	}
    }

		// *Add listAllItems() annotations* //
    public List<Item> listAllItems() {
        return repository.findAllItemsForUser(currentUser);
    }

		// *Add lookupItemById() annotations* //
    public Item lookupItemById(long id) {
        Item item = repository.findById(id);
        if (item == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return item;
    }


		// *Add createItem() annotations* //
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

		//*Add deleteItem() annotations*//
    public void deleteItem(long id) {
        itemService.remove(id);
    }
}
