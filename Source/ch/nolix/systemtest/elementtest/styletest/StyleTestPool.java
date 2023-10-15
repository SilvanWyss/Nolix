//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class StyleTestPool extends TestPool {

  // constructor
  public StyleTestPool() {
    super(DeepSelectingStyleTest.class, SelectingStyleTest.class, StyleTest.class);
  }
}
