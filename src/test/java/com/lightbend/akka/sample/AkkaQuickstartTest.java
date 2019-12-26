package com.lightbend.akka.sample;

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import com.lightbend.akka.sample.greeter.Greet;
import com.lightbend.akka.sample.greeter.Greeted;
import com.lightbend.akka.sample.greeter.Greeter;
import org.junit.ClassRule;
import org.junit.Test;

public class AkkaQuickstartTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();

    @Test
    public void testGreeterActorSendingOfGreeting() {
        TestProbe<Greeted> testProbe = testKit.createTestProbe();
        ActorRef<Greet> underTest = testKit.spawn(Greeter.create(), "greeter");
        underTest.tell(new Greet("Charles", testProbe.getRef()));
        testProbe.expectMessage(new Greeted("Charles", underTest));
    }

}
