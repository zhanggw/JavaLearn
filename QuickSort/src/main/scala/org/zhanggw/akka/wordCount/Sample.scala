package org.zhanggw.akka.wordCount

import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import akka.dispatch.ExecutionContexts
import scala.concurrent.Await
import scala.concurrent.duration._


/**
 * Created by zhanggw on 2016/5/24.
 */
object Sample extends App {
  implicit val ec = ExecutionContexts.global()
  override def main (args: Array[String]){
    val system = ActorSystem("Sample")
    val actorRef = system.actorOf(Props(new WordCounterActor(args(0))))
    implicit val timeout = Timeout(25 seconds)
    val future = actorRef ? StartProcessFileMsg()
    val result = Await.result(future, timeout.duration).asInstanceOf[Int]
    println(result)
  }
}
