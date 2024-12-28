package ch.nolix.systemtest.objectdatatest.entityflyweighttest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.modelflyweight.EntityFlyWeight;

final class EntityFlyWeightTest extends StandardTest {

  @Test
  void testCase_noteInsert() {

    //setup
    final var insertAction = Mockito.mock(Runnable.class);
    final var testUnit = EntityFlyWeight.withInsertAction(insertAction);

    //execution
    testUnit.noteInsert();

    //verification
    Mockito.verify(insertAction).run();
  }

  @Test
  void testCase_withInsertAction() {

    //setup
    final var insertAction = Mockito.mock(Runnable.class);

    //execution
    final var testUnit = EntityFlyWeight.withInsertAction(insertAction);

    //verification
    expect(testUnit.isEffectual()).isTrue();
  }

  @Test
  void testCase_withInsertAction_whenTheGivenInsertActionIsNull() {

    //execution & verification
    expectRunning(() -> EntityFlyWeight.withInsertAction(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given insert action is null.");
  }
}
