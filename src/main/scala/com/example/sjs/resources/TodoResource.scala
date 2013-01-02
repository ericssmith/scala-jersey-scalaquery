package com.example.sjs.resources

import javax.ws.rs._

import com.example.sjs.beans.TodoBean
import com.example.sjs.data.TodoDAO

import javax.ws.rs.core.{GenericEntity,Response,UriBuilder,Context,UriInfo}
import javax.ws.rs.core.Response.Status
import java.net.URI
import java.util.{List => JList}
import java.util


@Path("/todos")
class TodoResource {

  @Context
  val uriInfo: UriInfo = null

  val dao = new TodoDAO

  @GET
  @Produces(Array("application/json"))
  def listTodos() : Response = {
    val todos = dao.findAll()
    todos match {
      case Some(todos) => {
        val entity : GenericEntity[JList[TodoBean]] = new GenericEntity[util.List[TodoBean]](todos){}
        Response.ok(entity).build()
      }
      case None => Response.status(Status.NOT_FOUND).build()
    }
  }


  @Path("{id}")
  @GET
  @Produces(Array("application/json"))
  def getTodo(@PathParam("id") id: String) : Response = {
    val todo = dao.findById(id.toInt)
    todo match {
      case Some(todo) => Response.ok(todo).build()
      case None =>  Response.status(Status.NOT_FOUND).build()
    }
  }


  @POST
  @Consumes(Array("application/json"))
  @Produces(Array("application/json"))
  def postTodo(todo: TodoBean) : Response = {
    val createdTodo = dao.create(todo)
    val id = createdTodo.getId()
    val ub: UriBuilder = uriInfo.getAbsolutePathBuilder()
    val todoUri: URI = ub.path(createdTodo.getId()).build()
    return Response.created(todoUri).entity(createdTodo).build()
  }


  @Path("{id}")
  @PUT
  @Consumes(Array("application/json"))
  @Produces(Array("application/json"))
  def updateTodo(todo: TodoBean) : TodoBean = {
    val updatedTodo = dao.update(todo)
    updatedTodo
  }


  @Path("{id}")
  @DELETE
  def remove(@PathParam("id") id: String) : Unit = {
    dao.remove(id.toInt)
  }

}
