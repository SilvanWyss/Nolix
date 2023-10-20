//package declaration
package ch.nolix.coretest.mathtest.stochastictest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(final String[] arguments) {
    new StochasticTestPool().run();
  }
}
