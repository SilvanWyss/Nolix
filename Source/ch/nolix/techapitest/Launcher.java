//package declaration
package ch.nolix.techapitest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new TechApiTestPool().run();
  }
}
