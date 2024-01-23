//package declaration
package ch.nolix.coreapitest.environmentapitest.filesystemapitest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new FileSystemApiTestPool().run();
  }
}
