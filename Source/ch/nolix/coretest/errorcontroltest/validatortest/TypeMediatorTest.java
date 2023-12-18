//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.TypeMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class TypeMediatorTest extends Test {

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsNull() {

    //setup
    final var testUnit = new TypeMediator<>(null);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given type is null.");
  }

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsNotAbstract() {

    //setup
    final var testUnit = new TypeMediator<>(String.class);

    //execution & verify
    expectRunning(testUnit::isAbstract)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given type 'class java.lang.String' is not abstract.");
  }

  //method
  @TestCase
  public void testCase_isAbstract_whenTheGivenArgumentIsAbstract() {

    //setup
    final var testUnit = new TypeMediator<>(List.class);

    //execution & verify
    expectRunning(testUnit::isAbstract).doesNotThrowException();
  }
}
