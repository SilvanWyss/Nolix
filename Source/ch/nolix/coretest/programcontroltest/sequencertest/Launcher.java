//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//class
public final class Launcher {

  //constructor
  private Launcher() {
  }

  //main method
  public static void main(String[] args) {
    new SequencerTestPool().run();
  }
}
