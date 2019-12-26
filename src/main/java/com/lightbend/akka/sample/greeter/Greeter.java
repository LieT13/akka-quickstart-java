package com.lightbend.akka.sample.greeter;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Greeter extends AbstractBehavior<Greet> {

  public static Behavior<Greet> create() {
    return Behaviors.setup(Greeter::new);
  }

  private Greeter(ActorContext<Greet> context) {
    super(context);
  }

  @Override
  public Receive<Greet> createReceive() {
    return newReceiveBuilder().onMessage(Greet.class, this::onGreet).build();
  }

  private Behavior<Greet> onGreet(Greet command) {
    getContext().getLog().info("Hello {}!", command.whom);
    command.replyTo.tell(new Greeted(command.whom, getContext().getSelf()));
    return this;
  }

}
