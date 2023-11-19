//package declaration
package ch.nolix.systemtest.sqlrawdatabasetest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SqlRawDatabaseTestPool().run();
  }
}
