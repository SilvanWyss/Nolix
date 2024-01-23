//package declaration
package ch.nolix.coreapitest.environmentapitest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new EnvironmentApiTestPool().run();
  }
}
