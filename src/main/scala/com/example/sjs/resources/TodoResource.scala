package com.example.sjs.resources

import javax.ws.rs.{PathParam, Produces, GET, Path}


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
  def getTodo(@PathParam("id") id: String) : String = {
    "{\"title\":\"First todo\",\"description\":\"Do the first thing\",\"id\":\"" + id + "\"}"
  }

}
