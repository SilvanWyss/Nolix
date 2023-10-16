//package declaration
package ch.nolix.coretest.programstructuretest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.programstructuretest.cachingtest.CachingTestPool;

//class
public final class ProgramStructureTestPool extends TestPool {

  //constructor
  public ProgramStructureTestPool() {
    super(new CachingTestPool());
  }
}
