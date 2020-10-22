package com.akka.remote.client

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.akka.messages.{GetRequest, SetRequest}

import scala.concurrent.duration._

case class Client(url:String) {

  private implicit  val timeout=Timeout(4 seconds)


  //client端因为也是需要远程去连接服务器,所以也需要配置remote的参数,见配置文件.
  private implicit val system=ActorSystem("client-sys")


  private val remoteActor=system.actorSelection(s"akka.tcp://akka-db-sys@$url/user/akka-db")

  def set(key:String,value:Object)={
    remoteActor ? SetRequest(key,value)
  }
  def get(key:String)={
    remoteActor ? GetRequest(key)
  }




}
