//package declaration
package ch.nolix.coretest.programatomtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.programatomtest.functiontest.FunctionTestPool;
import ch.nolix.coretest.programatomtest.nametest.NameTestPool;

//class
public final class ProgramAtomTestPool extends TestPool {

  // constructor
  public ProgramAtomTestPool() {
    super(new FunctionTestPool(), new NameTestPool());
  }
}
