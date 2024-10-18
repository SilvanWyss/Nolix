package ch.nolix.coretest.errorcontroltest.validatortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class MultiDoubleMediatorTest extends StandardTest {

  @Test
  void testCase_arePositive_whenTheGivenArgumentsAreAllPositive() {

    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive).doesNotThrowException();
  }

  @Test
  void testCase_arePositive_whenOneOfTheGivenArgumentsIs0() {

    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 0.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given 6th argument '0.0' is not positive.");
  }

  @Test
  void testCase_arePositive_whenOneOfTheGivenArgumentsIsNegative() {

    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, -1.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given 6th argument '-1.0' is not positive.");
  }
}
