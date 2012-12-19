package com.example.sjs.data

import ConnectionHelper.getConnection

import org.scalaquery.ql.extended.{ExtendedTable => Table}
import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.extended.MySQLDriver.Implicit._

import com.example.sjs.beans.TodoBean

import scala.collection.JavaConversions._
import java.util.{List => JList}
import org.scalaquery.ql.{SimpleFunction, Query}


object TodoMap extends Table[(Int, String, String)]("todo") {
  def id = column[Int]("id", O.PrimaryKey)
  def title = column[String]("title")
  def description = column[String]("description")
  def * = id ~ title ~ description
}
 
class TodoDAO {
  val db  = getConnection

  def findById(id: Int) : TodoBean = {
    db withSession {
      val qry = for (t <- TodoMap if t.id is id) yield t.id ~ t.title ~ t.description
      val inter = qry mapResult {
        case(id, title, description) => new TodoBean(id.toString, title, description)
      }
      val result = inter.first
      result
    }
  }

  def findAll() : JList[TodoBean] = {
    db withSession {
      val qry = for (t <- TodoMap) yield t.id ~ t.title ~ t.description
      val inter = qry mapResult {
        case(id, title, description) => new TodoBean(id.toString, title, description)
      }
      val results = seqAsJavaList(inter.list)
      results
    }
  }


  def create(todo: TodoBean) : TodoBean = {
    var id: Int = -1
    db withSession {
      TodoMap.title ~ TodoMap.description insert(todo.title, todo.description)
      val idQuery = Query(SimpleFunction.nullary[Int]("LAST_INSERT_ID"))
      id = idQuery.list.head
    }
    val createdTodo = new TodoBean(id.toString, todo.title, todo.description)
    createdTodo
  }

}

