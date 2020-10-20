package com.akka.actor

import akka.actor.Actor
import akka.event.Logging
import com.akka.messages.SetRequest

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

    case other => log.info(
      s"""
         |receive unknown message  $other
         |""".stripMargin)

  }



}



