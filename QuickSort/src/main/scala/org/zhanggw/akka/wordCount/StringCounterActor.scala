package org.zhanggw.akka.wordCount

import akka.actor.Actor

/**
 * Created by zhanggw on 2016/5/24.
 */

case class ProcessStringMsg(str: String)
case class StringProcessedMsg(words: Int)

class StringCounterActor extends Actor{
  def receive = {
    case ProcessStringMsg(string) => {
      val wordInLines = string.split(" ").length
      sender() ! StringProcessedMsg(wordInLines)
    }
    case _ => {
      println("Error: message not recognized")
    }
  }
}
