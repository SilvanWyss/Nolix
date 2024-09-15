package ch.nolix.systemtest.objectdatatest.entityflyweighttest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.entityflyweight.VoidEntityFlyWeight;

final class VoidEntityFlyWeightTest extends StandardTest {

  @Test
  void testCase_isVoid() {

    //setup
    final var testUnit = new VoidEntityFlyWeight();

    //execution
    final var result = testUnit.isVoid();

    //verification
    expect(result);
  }

  @Test
  void testCase_setInsertAction() {

    //setup
    final var testUnit = new VoidEntityFlyWeight();

    //execution & verification
    expectRunning(() -> testUnit.setInsertAction(GlobalFunctionService::doNothing))
      .throwsException()
      .ofType(ArgumentDoesNotSupportMethodException.class);
  }
}
