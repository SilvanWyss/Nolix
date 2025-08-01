package ch.nolix.coretest.programcontrol.flowcontrol;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.programcontrol.flowcontrol.ForCountMediator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ForCountMediatorTest extends StandardTest {

  @Test
  void testCase_forMaxRunCount_whenTheGivenMaxRunCountIsNegative() {

    //execution & verification
    expectRunning(() -> ForCountMediator.forMaxRunCount(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given max run count '-1' is negative.");
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs0() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs1() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable).run();
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs5() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

    //execution
    testUnit.run(mockRunnable);

    //verification
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs0() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs1() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs5() {

    //setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

    //execution
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

    //verification
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }
}
