//package declaration
package ch.nolix.systemtest.objectdatabasetest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new ObjectDatabaseTestPool().run();
  }
}
