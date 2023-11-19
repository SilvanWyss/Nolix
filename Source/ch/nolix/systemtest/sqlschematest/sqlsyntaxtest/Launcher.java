//package declaration
package ch.nolix.systemtest.sqlschematest.sqlsyntaxtest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SqlSyntaxTestPool().run();
  }
}
