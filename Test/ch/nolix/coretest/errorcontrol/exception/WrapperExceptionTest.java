/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.errorcontrol.exception;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class WrapperExceptionTest extends StandardTest {
  @Test
  void testCase_forError() {
    //setup
    final var exception = new Exception();

    //execution
    final var result = WrapperException.forError(exception);

    //verification
    expect(result.getCause()).is(exception);
  }

  @Test
  void testCase_forErrorMessageAndError() {
    //setup
    final var errorMessage = "An Exception arrised.";
    final var exception = new Exception();

    //execution
    final var result = WrapperException.forErrorMessageAndError(errorMessage, exception);

    //verification
    expect(result.getMessage()).is(errorMessage);
    expect(result.getCause()).is(exception);
  }
}
