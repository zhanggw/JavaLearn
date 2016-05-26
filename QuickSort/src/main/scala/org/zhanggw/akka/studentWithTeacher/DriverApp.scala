package org.zhanggw.akka.studentWithTeacher

import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Props
import org.zhanggw.akka.studentWithTeacher.TeacherProtocol.InitSignal


/**
 * Created by zhanggw on 2016/5/25.
 */
object DriverApp extends App {
  val actorSystem = ActorSystem("UniversityMessageSystem")
  val teacherActorRef = actorSystem.actorOf(Props[TeacherActor], "teacher")
  val studentActorRef = actorSystem.actorOf(Props(new StudentActor(teacherActorRef)), "student")

  studentActorRef ! InitSignal
  Thread.sleep(2000)
  actorSystem.shutdown()
}
