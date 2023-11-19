//package declaration
package ch.nolix.systemtest.guitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.guitest.backgroundtest.BackgroundTestPool;

//class
public final class GuiTestPool extends TestPool {

  //constructor
  public GuiTestPool() {
    super(new BackgroundTestPool());
  }
}
