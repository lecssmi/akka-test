package com.akka.remote.server

import akka.actor.{ActorSystem, Props}
import com.akka.actor.AkkaDb

object Server extends App {
  val system = ActorSystem("akka-db-sys")
  //启动的时候,因为是远程,需要使用remote的解决方案,见配置文件.
  //配置文件默认application开头,但是可以使用-Dconfig.resource 等JVM选项另外指定.
  //默认的jar包里面是有reference.conf文件的,是所有actor的默认配置.自定义配置文件会将其覆盖.
  val actor = system.actorOf(Props[AkkaDb], "akka-db")
  println(actor.path)


}
