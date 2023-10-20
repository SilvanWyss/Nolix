//package declaration
package ch.nolix.systemtest.sqldatabasebasicschematest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SqlDatabaseBasicSchemaTestPool().run();
  }
}
