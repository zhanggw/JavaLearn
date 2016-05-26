package org.zhanggw.akka.studentWithTeacher

import akka.actor.{ActorLogging, Actor}
import TeacherProtocol._

/**
 * Created by zhanggw on 2016/5/25.
 */
class TeacherActor extends Actor with ActorLogging {
  val quotes = List("moderation is for cowards", "anything worth doing is worth overdoing",
                    "the trouble is you think you have time", "you naver gone know if you never ever try")
  def receive = {
    case QuoteRequest => {
      import util.Random

      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      sender() ! quoteResponse
    }
  }

  def quoteList = quotes

}
