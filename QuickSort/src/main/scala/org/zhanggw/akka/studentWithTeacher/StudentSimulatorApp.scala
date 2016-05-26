package org.zhanggw.akka.studentWithTeacher

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem
import TeacherProtocol.QuoteRequest

/**
 * Created by zhanggw on 2016/5/25.
 */
object StudentSimulatorApp extends App {
  val actorSystem = ActorSystem("UniversityMessageSystem")
  val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])
  teacherActorRef ! QuoteRequest
  Thread.sleep(2000)
  actorSystem.shutdown()
}
