//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class FunctionTestPool extends TestPool {

  //constructor
  public FunctionTestPool() {
    super(FunctionCatalogueTest.class, GlobalFunctionToolTest.class);
  }
}
