//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.test.StandardTest;

//class
final class GlobalSequencerTest extends StandardTest {

  //method
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

  //method
  @Test
  void testCase_runInBackground_whenPassingProcessIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(FunctionCatalogue::doNothing);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
  }

  //method
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
