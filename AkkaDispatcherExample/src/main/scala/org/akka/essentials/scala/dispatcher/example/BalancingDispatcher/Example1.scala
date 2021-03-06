package org.akka.essentials.scala.dispatcher.example.BalancingDispatcher
import akka.actor.ActorRef
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.actor.Props
import org.akka.essentials.scala.dispatcher.MsgEchoActor
import akka.routing.RandomRouter
import akka.routing.RoundRobinRouter

object Example1 {
	def main(args: Array[String]): Unit = {}
	val _system = ActorSystem.create("balancing-dispatcher",ConfigFactory.load().getConfig("MyDispatcherExample"))

	val actor = _system.actorOf(Props[MsgEchoActor].withDispatcher("balancingDispatcher").withRouter(
						RoundRobinRouter(5)))

	0 to 25 foreach {
		i => actor ! i
	}
  Thread.sleep(3000)
	_system.shutdown()
}