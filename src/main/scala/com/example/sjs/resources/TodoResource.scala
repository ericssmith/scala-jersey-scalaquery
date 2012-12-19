package com.example.sjs.resources

import javax.ws.rs.{PathParam, Produces, GET, Path}

import com.example.sjs.beans.Todo
import com.example.sjs.data.TodoDAO


@Path("/todos")
class TodoResource {

  val dao = new TodoDAO

  @GET
  @Produces(Array("application/json"))
  def listTodos(): String = {
    "listTodos"
  }


  @Path("{id}")
  @GET
  @Produces(Array("application/json"))
  def getTodo(@PathParam("id") id: String) : Todo = {
    val todo : Todo = dao.findById(id.toInt)
    todo
  }

}
