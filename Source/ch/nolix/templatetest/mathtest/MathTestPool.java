//package declaration
package ch.nolix.templatetest.mathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.mathtest.sequencetest.SequenceTestPool;

//class
public final class MathTestPool extends TestPool {

  //constructor
  public MathTestPool() {
    super(new SequenceTestPool());
  }
}
