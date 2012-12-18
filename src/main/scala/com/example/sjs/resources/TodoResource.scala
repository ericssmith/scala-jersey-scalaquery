package com.example.sjs.resources

import javax.ws.rs.{Produces, GET, Path}


@Path("/todos")
class TodoResource {

  @GET
  @Produces(Array("application/json"))
  def sayHello(): String = {
    "Hello World"
  }

}
