package com.akka.actor

import akka.actor.{Actor, ActorSystem, Props, Status}

class ScalaPongActor(response:String) extends Actor{

  override def receive: Receive = {
    case "ping" =>sender() ! response
    case other =>sender() ! Status.Failure(new Exception(s""" unknows message $other"""))


  }


}

object ScalaPongActor{

  def props(response:String): Props ={
    Props(classOf[ScalaPongActor],response)
  }

  def main(args: Array[String]): Unit = {

    val system = ActorSystem()
    //Props第一个参数是actor的类型,后面的参数是actor的构造器参数.
    val actor = system.actorOf(ScalaPongActor props "response")
    println(
      s"""
         |actor path: ${actor.path}
         |""".stripMargin)
    actor ! "ping"


  }
}




