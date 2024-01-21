//package declaration
package ch.nolix.coretest.programcontroltest.sequencertest;

//Mockito imports
import org.mockito.Mockito;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.programcontrol.sequencer.ForCountMediator;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ForCountMediatorTest extends Test {

  //method
  @TestCase
  public void testCase_forMaxRunCount_whenTheGivenMaxRunCountIsNegative() {

    //execution & verification
    expectRunning(() -> ForCountMediator.forMaxRunCount(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given max run count '-1' is negative.");
  }

  //method
  @TestCase
  public void testCase_run_whenTheGivenMaxRunCountIs0() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  //method
  @TestCase
  public void testCase_run_whenTheGivenMaxRunCountIs1() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable).run();
  }

  //method
  @TestCase
  public void testCase_run_whenTheGivenMaxRunCountIs5() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }

  //method
  @TestCase
  public void testCase_runInBackground_whenTheGivenMaxRunCountIs0() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  //method
  @TestCase
  public void testCase_runInBackground_whenTheGivenMaxRunCountIs1() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
    Mockito.verify(mockRunnable).run();
  }

  //method
  @TestCase
  public void testCase_runInBackground_whenTheGivenMaxRunCountIs5() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully());
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }
}