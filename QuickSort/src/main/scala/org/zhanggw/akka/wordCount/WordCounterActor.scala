package org.zhanggw.akka.wordCount

import akka.actor.{Actor, ActorRef, Props}

import scala.io.Source

/**
 * Created by zhanggw on 2016/5/24.
 */

case class StartProcessFileMsg()

class WordCounterActor(filename: String) extends Actor{
  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None
  def receive = {
    case StartProcessFileMsg() => {
      if (running) println("Warning: duplicate start message received")
      else {
        running = true
        fileSender = Some(sender)
        Source.fromFile(filename).getLines().foreach {
          line =>
            context.actorOf(Props[StringCounterActor]) ! ProcessStringMsg(line)
            totalLines += 1
        }
      }
    }
    case StringProcessedMsg(words) => {
      result += words
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.map(_ ! result)  // provide result to process invoker
      }
    }
    case _ => println("message not recognized!")
  }
}
