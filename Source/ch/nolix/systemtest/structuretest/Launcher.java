//package declaration
package ch.nolix.systemtest.structuretest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new StructureTestPool().run();
  }
}
