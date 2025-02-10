package ch.nolix.core.programcontrol.sequencer;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.programcontrol.flowcontrol.GlobalFlowController;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GlobalSequencerTest extends StandardTest {

  @Test
  void testCase_runInBackground_whenFailingProcessIsGiven() {

    //execution
    final var result = GlobalFlowController.runInBackground(() -> {
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
    final var result = GlobalFlowController.runInBackground(GlobalFunctionService::doNothing);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
  }

  @Test
  void testCase_runInBackground_whenFunctionIsGiven() {

    //execution
    final var result = GlobalFlowController.runInBackground(() -> 3 + 4);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
    expect(result.getResult()).isEqualTo(7);
  }
}
