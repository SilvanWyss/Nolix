/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.programcontrol.flowcontrol;

import org.junit.jupiter.api.Test;

import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.util.FunctionService;

/**
 * @author Silvan Wyss
 */
final class FlowControllerTest extends StandardTest {
  @Test
  void testCase_runInBackground_whenFailingProcessIsGiven() {
    // execute
    final var result = FlowController.runInBackground(() -> {
      throw GeneralException.withErrorMessage("test error");
    });
    result.waitUntilIsFinished();

    // verify
    expect(result.isFinishedWithError()).isTrue();
    expect(result.getError()).isOfType(GeneralException.class);
  }

  @Test
  void testCase_runInBackground_whenPassingProcessIsGiven() {
    // execute
    final var result = FlowController.runInBackground(FunctionService::doNothing);
    result.waitUntilIsFinished();

    // verify
    expect(result.isFinishedSuccessfully()).isTrue();
  }

  @Test
  void testCase_runInBackground_whenFunctionIsGiven() {
    // execute
    final var result = FlowController.runInBackground(() -> 3 + 4);
    result.waitUntilIsFinished();

    // verify
    expect(result.isFinishedSuccessfully()).isTrue();
    expect(result.getResult()).isEqualTo(7);
  }
}
