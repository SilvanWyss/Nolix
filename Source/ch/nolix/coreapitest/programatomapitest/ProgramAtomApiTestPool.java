//package declaration
package ch.nolix.coreapitest.programatomapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.programatomapitest.variableapitest.VariableApiTestPool;

//class
public final class ProgramAtomApiTestPool extends TestPool {

  //constructor
  public ProgramAtomApiTestPool() {
    super(new VariableApiTestPool());
  }
}
