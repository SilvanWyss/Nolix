//package declaration
package ch.nolix.coreapitest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new CoreApiTestPool().run();
  }
}
