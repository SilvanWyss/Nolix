/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.programcontrol.flowcontrol;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.programcontrol.flowcontrol.ForCountMediator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;

/**
 * @author Silvan Wyss
 */
final class ForCountMediatorTest extends StandardTest {
  @Test
  void testCase_forMaxRunCount_whenTheGivenMaxRunCountIsNegative() {
   // execute & verification
    expectRunning(() -> ForCountMediator.forMaxRunCount(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given max run count '-1' is negative.");
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs0() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

   // execute
    testUnit.run(mockRunnable);

   // verify
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs1() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

   // execute
    testUnit.run(mockRunnable);

   // verify
    Mockito.verify(mockRunnable).run();
  }

  @Test
  void testCase_run_whenTheGivenMaxRunCountIs5() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

   // execute
    testUnit.run(mockRunnable);

   // verify
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs0() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(0);

   // execute
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

   // verify
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable, Mockito.never()).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs1() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(1);

   // execute
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

   // verify
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable).run();
  }

  @Test
  void testCase_runInBackground_whenTheGivenMaxRunCountIs5() {
    // setup
    final var mockRunnable = Mockito.mock(Runnable.class);
    final var testUnit = ForCountMediator.forMaxRunCount(5);

   // execute
    final var result = testUnit.runInBackground(mockRunnable);
    result.waitUntilIsFinished();

   // verify
    expect(result.isFinishedSuccessfully()).isTrue();
    Mockito.verify(mockRunnable, Mockito.times(5)).run();
  }
}
