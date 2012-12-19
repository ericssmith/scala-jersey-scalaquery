package com.example.sjs.data


import java.util.ResourceBundle
import org.scalaquery.session.Database


object ConnectionHelper {

  def getConnection : Database = {
    val bundle: ResourceBundle = ResourceBundle.getBundle("jdbc")
    val driver = bundle.getString("jdbc.driver")
    val user = bundle.getString("jdbc.username")
    val password = bundle.getString("jdbc.password")
    val url = bundle.getString("jdbc.url")
    val db = Database.forURL(url, driver = driver, user=user, password=password)
    db
  }

}
