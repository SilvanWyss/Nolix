//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new AtomicControlTestPool().run();
  }
}
