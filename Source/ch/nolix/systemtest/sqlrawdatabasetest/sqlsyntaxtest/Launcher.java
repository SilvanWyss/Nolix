//package delcaration
package ch.nolix.systemtest.sqlrawdatabasetest.sqlsyntaxtest;

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