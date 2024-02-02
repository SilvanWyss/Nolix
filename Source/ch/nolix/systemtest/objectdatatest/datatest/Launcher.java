//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new DataTestPool().run();
  }
}
