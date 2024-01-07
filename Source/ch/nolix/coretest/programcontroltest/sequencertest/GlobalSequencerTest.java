//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.programatomapi.functionapi.FunctionCatalogue;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class GlobalSequencerTest extends Test {

  //method
  @TestCase
  public void testCase_runInBackground_whenFailingProcessIsGiven() {

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
  @TestCase
  public void testCase_runInBackground_whenPassingProcessIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(FunctionCatalogue::doNothing);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
  }

  //method
  @TestCase
  public void testCase_runInBackground_whenFunctionIsGiven() {

    //execution
    final var result = GlobalSequencer.runInBackground(() -> 3 + 4);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
    expect(result.getResult()).isEqualTo(7);
  }
}
