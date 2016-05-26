package org.zhanggw.akka.remoteActor

import akka.actor._

/**
 * Created by zhanggw on 2016/5/26.
 */

object HelloLocal extends App {
  System.setProperty("akka.remote.netty.port", "5152")
  implicit val system = ActorSystem("LocalSystem")
  val localActor = system.actorOf(Props[LocalActor], name = "local")
  localActor ! Start
}

class LocalActor extends Actor {
  val remote = context.actorFor("akka.tcp://HelloRemoteSystem@127.0.0.1:5150/user/RemoteActor")
  var counter = 0

  def receive = {
    case Start => {
      remote ! Message("Hello from the LocalActor")
    }
    case Message(msg) => {
      println(s"LocalActor received message: '$msg'")
      if(counter < 5) {
        sender ! Message("Hello back to you")
        counter += 1
      }
    }
    case _ => {
      println("LocalActor got something unexpected.")
    }
  }
}
