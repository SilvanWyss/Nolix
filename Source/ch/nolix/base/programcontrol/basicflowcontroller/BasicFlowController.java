/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.basicflowcontroller;

import java.util.function.BooleanSupplier;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.unitconversioncatalog.TimeUnitConversionCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class BasicFlowController {
  private BasicFlowController() {
  }

  /**
   * Waits as long as the given condition is fulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null
   */
  public static void waitAsLongAs(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableNameCatalog.CONDITION).isNotNull();

    var i = 1;

    while (condition.getAsBoolean()) {
      if (i < 100) {
        waitForMilliseconds(10);
        i++;
      } else {
        waitForMilliseconds(100);
      }
    }
  }

  /**
   * Waits for the given duractionInSeconds.
   * 
   * @param durationInSeconds
   * @throws RuntimeException if the given duractionInSeconds is negative
   */
  public static void waitForSeconds(final int durationInSeconds) {
    final var durationInMilliseconds = TimeUnitConversionCatalog.MILLISECONDS_PER_SECOND * durationInSeconds;

    waitForMilliseconds(durationInMilliseconds);
  }

  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @throws RuntimeException if the given durationInMilliseconds is negative
   */
  public static void waitForMilliseconds(final int durationInMilliseconds) {
    Validator.assertThat(durationInMilliseconds).thatIsNamed("duration in milliseconds").isNotNegative();

    try {
      Thread.sleep(durationInMilliseconds);
    } catch (final InterruptedException interruptedException) {
      Thread.currentThread().interrupt();

      throw WrapperException.forError(interruptedException);
    }
  }

  /**
   * Waits until the given condition is fulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null
   */
  public static void waitUntil(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableNameCatalog.CONDITION).isNotNull();

    waitAsLongAs(() -> !condition.getAsBoolean());
  }
}
