package org.zhanggw.akka.studentWithTeacher

/**
 * Created by zhanggw on 2016/5/25.
 */
object TeacherProtocol {
  case class InitSignal()
  case class QuoteRequest()
  case class QuoteResponse(quoteString: String)
}
