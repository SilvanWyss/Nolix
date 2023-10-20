//package declaration
package ch.nolix.coretest.programstructuretest.cachingtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new CachingTestPool().run();
  }
}
