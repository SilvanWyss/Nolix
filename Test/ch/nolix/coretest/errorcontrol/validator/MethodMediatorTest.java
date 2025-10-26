package ch.nolix.coretest.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.MethodMediator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class MethodMediatorTest extends StandardTest {
  @Test
  void testCase_hasReturnType_whenIsNull() {
    //setup
    final var testUnit = MethodMediator.forArgument(null);

    //execution & verification
    expectRunning(() -> testUnit.hasReturnType(String.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given method does not have the return type 'java.lang.String'.");
  }
}
