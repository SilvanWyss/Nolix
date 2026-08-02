/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.programcontrol.process;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.FinishRequestable;

/**
 * @author Silvan Wyss
 */
final class FinishRequestableTest extends StandardTest {
  @Test
  void testCase_isRunning_whenIsNotFinished() {
    // setup
    final var testUnit = new FinishRequestable() {
      @Override
      public boolean isFinished() {
        return false;
      }
    };

    // execute
    final var result = testUnit.isRunning();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isRunning_whenIsFinished() {
    // setup
    final var testUnit = new FinishRequestable() {
      @Override
      public boolean isFinished() {
        return true;
      }
    };

    // execute
    final var result = testUnit.isRunning();

    // verify
    expect(result).isFalse();
  }
}
