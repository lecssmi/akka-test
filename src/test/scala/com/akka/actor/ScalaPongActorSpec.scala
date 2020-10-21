package com.akka.actor

import akka.actor.{ActorSystem, actorRef2Scala}
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.{Await, Future}
import scala.reflect.runtime.universe.typeOf
import scala.util.{Failure, Success}

class ScalaPongActorSpec extends FunSpecLike with Matchers{
  implicit  val sysmtem=ActorSystem()

  describe("a scala  poing actor"){
    describe("received a message from other"){
      it("should send the sender a response "){
        val actor = TestActorRef(new ScalaPongActor("hello "))
        actor ! "ping "

      }

    }
  }
  import scala.concurrent.duration._
  implicit  val timeout=Timeout(5 seconds)
  describe("pong actor"){
    val actor=TestActorRef(new ScalaPongActor("pong"))
    def askPong(message:String)= (actor? message).mapTo[String]
    it("should response with pong "){
      //?是带有返回结果的send,会返回一个结果
      val future=actor ? "ping"
      //Await强制当前线程阻塞,等待结果,并设置一个等待时间,不至于永久等待下去.
      val result=Await.result(future.mapTo[String], 1 second)
      result should  equal ("pong")


    }

    it("should fail on unknown message"){

      val future=actor ? "abc"
     //intercept方法其实类似于异常捕捉.
      intercept[Exception]{
        val result = Await.result(future.mapTo[String], 1 second)
      }
      import scala.concurrent.ExecutionContext.Implicits.global
      askPong("ping").recover {
         //recover方法会将有异常的部分转换,如果是没有异常,成功返回的结果,仍然会被传到下一步.
        case e:Exception=>"world"

      }.flatMap(x=>{
          //如果有异常,flatMap是没法继续进行的.
//          println(x)
          askPong(x)
        }).recoverWith{
            //recoverWith是返回一个Futre[U],而recovery是返回一个U,就这点区别
        case e:Exception=>askPong("ping")
      }.onComplete{
          case Success(value)=>println(s"""成功了,收到回复 $value""")
          case Failure(exception)=>{
            println("失败.")
            exception.printStackTrace()
          }
        }

    }

    it("should change the format"){

      import scala.reflect.runtime.universe._
      import scala.concurrent.ExecutionContext.Implicits.global
      val list = List("ping", "ping").map(askPong(_))
      println(list)
      val atered = Future.sequence(list)
      //sequence并不是简单的将结果转换,如果没有下面这个sleep,上面的List[Future]已经有结果,而下面这个Future[List]却还是
      //未完成状态.说明改方法本身是一个异步操作,返回一个Future.
//      Thread.sleep(1000)
      println(atered)

    }



  }

}
