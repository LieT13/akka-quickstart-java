package com.lightbend.akka.sample;

import akka.actor.typed.ActorSystem;
import com.lightbend.akka.sample.main.GreeterMain;
import com.lightbend.akka.sample.main.Start;

import java.io.IOException;

public class AkkaQuickstart {

  public static void main(String[] args) {
    //#actor-system
    final ActorSystem<Start> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");
    //#actor-system

    //#main-send-messages
    greeterMain.tell(new Start("Charles"));
    //#main-send-messages

    try {
      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ignored) {
    } finally {
      greeterMain.terminate();
    }
  }

}
