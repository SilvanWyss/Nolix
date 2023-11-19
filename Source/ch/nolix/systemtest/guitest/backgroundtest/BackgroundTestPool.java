//package declaration
package ch.nolix.systemtest.guitest.backgroundtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BackgroundTestPool extends TestPool {

  //constructor
  public BackgroundTestPool() {
    super(BackgroundTest.class);
  }
}
