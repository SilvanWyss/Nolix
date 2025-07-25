package ch.nolix.coreapitest.programcontrolapi.processapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.programcontrol.process.FinishRequestable;

final class FinishRequestableTest extends StandardTest {

  @Test
  void testCase_isRunning_whenIsNotFinished() {

    //setup
    final var testUnit = new FinishRequestable() {

      @Override
      public boolean isFinished() {
        return false;
      }
    };

    //execution
    final var result = testUnit.isRunning();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isRunning_whenIsFinished() {

    //setup
    final var testUnit = new FinishRequestable() {

      @Override
      public boolean isFinished() {
        return true;
      }
    };

    //execution
    final var result = testUnit.isRunning();

    //verification
    expect(result).isFalse();
  }
}
