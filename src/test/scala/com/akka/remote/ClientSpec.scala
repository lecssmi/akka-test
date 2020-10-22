package com.akka.remote

import com.akka.remote.client.Client
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class ClientSpec extends FunSpecLike with Matchers{

  val client=Client("127.0.0.1:2552")

  describe(" abc "){
    it("should be success"){

      client.set("123",new Integer(123))
      val future = client.get("123").mapTo[Object]
      val result = Await.result(future, 10 seconds)
      println(result)



    }
  }

}
