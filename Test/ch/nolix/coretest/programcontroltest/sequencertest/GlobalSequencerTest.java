package ch.nolix.coretest.programcontroltest.sequencertest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GlobalSequencerTest extends StandardTest {

  @Test
  void testCase_runInBackground_whenFailingProcessIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(() -> {
      throw GeneralException.withErrorMessage("test error");
    });
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedWithError());
    expect(result.getError()).isOfType(GeneralException.class);
  }

  @Test
  void testCase_runInBackground_whenPassingProcessIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(GlobalFunctionService::doNothing);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
  }

  @Test
  void testCase_runInBackground_whenFunctionIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(() -> 3 + 4);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
    expect(result.getResult()).isEqualTo(7);
  }
}
