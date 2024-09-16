package ch.nolix.systemtest.objectdatatest.entityflyweighttest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.entityflyweight.EntityFlyWeight;

final class EntityFlyWeightTest extends StandardTest {

  @Test
  void testCase_isVoid() {

    //setup
    final var testUnit = new EntityFlyWeight();

    //execution
    final var result = testUnit.isVoid();

    //verification
    expectNot(result);
  }

  @Test
  void testCase_noteInsert_whenDoesNotHaveInsertAction() {

    //setup
    final var testUnit = new EntityFlyWeight();

    //execution & verification
    expectRunning(testUnit::noteInsert).doesNotThrowException();
  }

  @Test
  void testCase_noteInsert_whenHasInsertAction() {

    //setup
    final var insertAction = Mockito.mock(Runnable.class);
    final var testUnit = new EntityFlyWeight();
    testUnit.setInsertAction(insertAction);

    //execution
    testUnit.noteInsert();

    //verification
    Mockito.verify(insertAction).run();
  }

  @Test
  void testCase_setInsertAction_whenGivenInsertActionIsNull() {

    //setup
    final var testUnit = new EntityFlyWeight();

    //execution & verification
    expectRunning(() -> testUnit.setInsertAction(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given insert action is null.");
  }
}
