import akka.actor.Actor

import scala.collection.mutable.ListBuffer


case class Quote(name: String, symbol: String, price: Double)


class MarketDataActor extends Actor  {

  val cache: ListBuffer[Quote] = scala.collection.mutable.ListBuffer.empty[Quote]

  override def preStart(): Unit = {
    context.system.eventStream.subscribe(context.self, classOf[Quote])
  }

  def receive: PartialFunction[Any, Unit] = {
    case Quote(name, symbol, price) =>
        cache += Quote(name, symbol, price)
  }
}
