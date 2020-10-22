package com.akka.actor

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.akka.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable

class AkkaDb extends Actor{
  val map=new mutable.HashMap[String,Object]()
  val log=Logging(context.system,this)
  override def receive: Receive = {
    case SetRequest(key, value) =>
      log.info(
        s"""
           |receive SetRequest - key :$key  , value :$value
           |""".stripMargin)
      map.put(key, value)
    case GetRequest(key)=>{

      log.info(s"receive GetRequest key:$key")
      map.get(key) match {
        case Some(x)=>sender() ! x
        case None=>sender() ! Status.Failure( KeyNotFoundException(key))

      }
    }


    case other => {
      log.info(
        s"""
           |receive unknown message  $other
           |""".stripMargin)
      Status.Failure(new ClassNotFoundException)
    }

  }



}



