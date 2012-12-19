package com.example.sjs.beans

import scala.reflect.BeanProperty
import javax.xml.bind.annotation.{XmlType, XmlRootElement}


@XmlRootElement(name="todo")
@XmlType(propOrder = Array("id", "title", "description"))
class TodoBean(@BeanProperty var id: String,
           @BeanProperty var title: String,
           @BeanProperty var description: String) {

  private def this() = this("","","")

}

