package com.lightbend.akka.sample.greeter;

import akka.actor.typed.ActorRef;

import java.util.Objects;

public class Greeted {

    public final String whom;
    public final ActorRef<Greet> from;

    public Greeted(String whom, ActorRef<Greet> from) {
        this.whom = whom;
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeted greeted = (Greeted) o;
        return Objects.equals(whom, greeted.whom) &&
                Objects.equals(from, greeted.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whom, from);
    }

    @Override
    public String toString() {
        return "Greeted{" +
                "whom='" + whom + '\'' +
                ", from=" + from +
                '}';
    }

}
