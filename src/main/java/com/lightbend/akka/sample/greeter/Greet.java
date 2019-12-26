package com.lightbend.akka.sample.greeter;

import akka.actor.typed.ActorRef;

public class Greet {

    public final String whom;
    public final ActorRef<Greeted> replyTo;

    public Greet(String whom, ActorRef<Greeted> replyTo) {
        this.whom = whom;
        this.replyTo = replyTo;
    }

}
