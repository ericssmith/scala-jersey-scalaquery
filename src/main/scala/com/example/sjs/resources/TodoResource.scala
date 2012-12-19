package com.example.sjs.resources

import javax.ws.rs._

import com.example.sjs.beans.TodoBean
import com.example.sjs.data.TodoDAO

import scala.collection.JavaConversions._
import java.util.{List => JList}


@Path("/todos")
class TodoResource {

  val dao = new TodoDAO

  @GET
  @Produces(Array("application/json"))
  def listTodos() : JList[TodoBean] = {
    val todos = dao.findAll()
    todos
  }


  @Path("{id}")
  @GET
  @Produces(Array("application/json"))
  def getTodo(@PathParam("id") id: String) : TodoBean = {
    val todo = dao.findById(id.toInt)
    todo
  }

  @POST
  @Consumes(Array("application/json"))
  @Produces(Array("application/json"))
  def postTodo(todo: TodoBean) : TodoBean = {
    var createdTodo = dao.create(todo)
    createdTodo
  }


}
