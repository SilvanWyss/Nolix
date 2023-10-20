//package declaration
package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new MultiStateConfigurationTestPool().run();
  }
}
