//package declaration
package ch.nolix.coretest.environmenttest.filesystemtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new FileSystemTestPool().run();
  }
}
