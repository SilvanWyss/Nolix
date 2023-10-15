//package declaration
package ch.nolix.systemtest.databaseapplicationtest;

//class
public final class Launcher {

  // main method
  public static void main(String[] args) {
    new DatabaseApplicationTestPool().run();
  }

  // constructor
  private Launcher() {
  }
}
