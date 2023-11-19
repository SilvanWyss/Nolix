//package declaration
package ch.nolix.systemtest.guitest.backgroundtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new BackgroundTestPool().run();
  }
}
