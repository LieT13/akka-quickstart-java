package com.lightbend.akka.sample.main;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import com.lightbend.akka.sample.GreeterBot;
import com.lightbend.akka.sample.greeter.Greet;
import com.lightbend.akka.sample.greeter.Greeted;
import com.lightbend.akka.sample.greeter.Greeter;

public class GreeterMain extends AbstractBehavior<Start> {

    private final ActorRef<Greet> greeter;

    public static Behavior<Start> create() {
        return Behaviors.setup(GreeterMain::new);
    }

    private GreeterMain(ActorContext<Start> context) {
        super(context);
        greeter = context.spawn(Greeter.create(), "greeter");
    }

    @Override
    public Receive<Start> createReceive() {
        return newReceiveBuilder().onMessage(Start.class, this::onStart).build();
    }

    private Behavior<Start> onStart(Start command) {
        ActorRef<Greeted> replyTo = getContext().spawn(GreeterBot.create(3), command.name);
        greeter.tell(new Greet(command.name, replyTo));
        return this;
    }

}
