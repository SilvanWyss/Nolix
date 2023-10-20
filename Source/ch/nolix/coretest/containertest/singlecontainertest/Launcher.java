//package info
package ch.nolix.coretest.containertest.singlecontainertest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SingleContainerTestPool().run();
  }
}
