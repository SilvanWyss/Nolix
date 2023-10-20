//package declaration
package ch.nolix.systemtest.graphictest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new GraphicTestPool().run();
  }
}
