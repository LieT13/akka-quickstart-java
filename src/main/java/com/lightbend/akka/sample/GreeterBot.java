package com.lightbend.akka.sample;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import com.lightbend.akka.sample.greeter.Greet;
import com.lightbend.akka.sample.greeter.Greeted;

public class GreeterBot extends AbstractBehavior<Greeted> {

    private final int max;
    private int greetingCounter;

    public static Behavior<Greeted> create(int max) {
        return Behaviors.setup(context -> new GreeterBot(context, max));
    }

    private GreeterBot(ActorContext<Greeted> context, int max) {
        super(context);
        this.max = max;
    }

    @Override
    public Receive<Greeted> createReceive() {
        return newReceiveBuilder().onMessage(Greeted.class, this::onGreeted).build();
    }

    private Behavior<Greeted> onGreeted(Greeted message) {
        greetingCounter++;
        getContext().getLog().info("Greeting {} for {}", greetingCounter, message.whom);
        if (greetingCounter == max) {
            return Behaviors.stopped();
        } else {
            message.from.tell(new Greet(message.whom, getContext().getSelf()));
            return this;
        }
    }

}
