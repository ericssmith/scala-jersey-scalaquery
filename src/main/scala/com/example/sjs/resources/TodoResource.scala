package com.example.sjs.resources

import javax.ws.rs.{PathParam, Produces, GET, Path}

import com.example.sjs.beans.Todo


@Path("/todos")
class TodoResource {

  @GET
  @Produces(Array("application/json"))
  def listTodos(): String = {
    "listTodos"
  }


  @Path("{id}")
  @GET
  @Produces(Array("application/json"))
  def getTodo(@PathParam("id") id: String) : Todo = {

    val todo = new Todo("1","First todo","Do the first thing")
    todo

  }

}
