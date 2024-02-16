//package declaration
package ch.nolix.systemapitest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SystemApiTestPool().run();
  }
}
