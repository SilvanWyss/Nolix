//package declaration
package ch.nolix.systemtest.structuretest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class StructureTestPool extends TestPool {

  // constructor
  public StructureTestPool() {
    super(
        AbsoluteOrRelativeIntTest.class);
  }
}
