//package declaration
package ch.nolix.systemtest.sqlrawschematest.databaseinitializertest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new DatabaseInitializerTestPool().run();
  }
}
