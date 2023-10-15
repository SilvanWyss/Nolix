//package declaration
package ch.nolix.systemtest.applicationtest;

//class
public final class Launcher {

  // main method
  public static void main(String[] args) {
    new ApplicationTestPool().run();
  }

  // constructor
  private Launcher() {
  }
}
