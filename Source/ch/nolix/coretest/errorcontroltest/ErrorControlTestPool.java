//package declaration
package ch.nolix.coretest.errorcontroltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.errorcontroltest.invalidargumentexceptiontest.InvalidArgumentExceptionTestPool;
import ch.nolix.coretest.errorcontroltest.validatortest.ValidatorTestPool;

//class
public final class ErrorControlTestPool extends TestPool {

  // constructor
  public ErrorControlTestPool() {
    super(new InvalidArgumentExceptionTestPool(), new ValidatorTestPool());
  }
}
