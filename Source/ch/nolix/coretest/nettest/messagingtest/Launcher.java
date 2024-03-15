//package declaration
package ch.nolix.coretest.nettest.messagingtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(final String[] arguments) {
    new MessagingTestPool().run();
  }
}
