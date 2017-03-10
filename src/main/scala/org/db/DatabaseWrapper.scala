package org.db

import org.mongodb.scala._

/**
  * Created by deweirich on 3/10/17.
  */
object DatabaseWrapper {

  def connectToServer() = {
    val mongoClient: MongoClient = MongoClient("mongodb://192.168.128.210:3001/")

    val database: MongoDatabase = mongoClient.getDatabase("meteor")
    val collection: MongoCollection[Document] = database.getCollection("locations")

    collection.find().map(s => println(s))
  }
}