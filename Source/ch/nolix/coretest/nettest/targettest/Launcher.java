//package declaration
package ch.nolix.coretest.nettest.targettest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(final String[] arguments) {
    new TargetTestPool().run();
  }
}
