package org.zhanggw.akka.studentWithTeacher

import akka.actor.{ActorLogging, ActorRef, Actor}
import TeacherProtocol._

/**
 * Created by zhanggw on 2016/5/25.
 */
class StudentActor(teacherActorRef: ActorRef) extends Actor with ActorLogging{
  def receive = {
    case InitSignal => {
      teacherActorRef ! QuoteRequest
    }

    case QuoteResponse(quoteString) => {
      log.info ("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
    }
  }
}
