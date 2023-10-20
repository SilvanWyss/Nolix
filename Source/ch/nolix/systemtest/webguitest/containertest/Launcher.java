//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new ContainerTestPool().run();
  }
}
