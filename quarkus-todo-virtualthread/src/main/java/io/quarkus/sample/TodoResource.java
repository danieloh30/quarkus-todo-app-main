package io.quarkus.sample;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;


@Path("/api")
public class TodoResource {

    private void log() {
		Log.infof("Called on %s", Thread.currentThread());
	}

    @OPTIONS
    @NonBlocking
    public Response opt() {
        return Response.ok().build();
    }

    @GET
    @RunOnVirtualThread
    public List<Todo> getAll() {
        log();
        return Todo.listAll(Sort.by("order"));
    }

    @GET
    @Path("/{id}")
    @RunOnVirtualThread
    public Todo getOne(@PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @Transactional
    @RunOnVirtualThread
    public Response create(@Valid Todo item) {
        log();
        item.persist();
        return Response.status(Status.CREATED).entity(item).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @RunOnVirtualThread
    public Response update(@Valid Todo todo, @PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        entity.id = id;
        entity.completed = todo.completed;
        entity.order = todo.order;
        entity.title = todo.title;
        entity.url = todo.url;
        return Response.ok(entity).build();
    }

    @DELETE
    @Transactional
    @RunOnVirtualThread
    public Response deleteCompleted() {
        log();
        Todo.deleteCompleted();
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RunOnVirtualThread
    public Response deleteOne(@PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        entity.delete();
        return Response.noContent().build();
    }

}