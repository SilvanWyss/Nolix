package ch.nolix.core.programcontrol.sequencer;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.FunctionService;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.core.testing.standardtest.StandardTest;

final class FlowControllerTest extends StandardTest {

  @Test
  void testCase_runInBackground_whenFailingProcessIsGiven() {

    //execution
    final var result = FlowController.runInBackground(() -> {
      throw GeneralException.withErrorMessage("test error");
    });
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedWithError()).isTrue();
    expect(result.getError()).isOfType(GeneralException.class);
  }

  @Test
  void testCase_runInBackground_whenPassingProcessIsGiven() {

    //execution
    final var result = FlowController.runInBackground(FunctionService::doNothing);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
  }

  @Test
  void testCase_runInBackground_whenFunctionIsGiven() {

    //execution
    final var result = FlowController.runInBackground(() -> 3 + 4);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
    expect(result.getResult()).isEqualTo(7);
  }
}
