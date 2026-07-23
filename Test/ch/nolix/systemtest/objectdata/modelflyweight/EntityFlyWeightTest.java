/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.modelflyweight;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.objectdata.modelflyweight.EntityFlyWeight;

/**
 * @author Silvan Wyss
 */
final class EntityFlyWeightTest extends StandardTest {
  @Test
  void testCase_noteInsert() {
    // setup
    final var insertAction = Mockito.mock(Runnable.class);
    final var testUnit = EntityFlyWeight.withInsertAction(insertAction);

    // execute
    testUnit.noteInsertIntoDatabase();

    // verify
    Mockito.verify(insertAction).run();
  }

  @Test
  void testCase_withInsertAction() {
    // setup
    final var insertAction = Mockito.mock(Runnable.class);

    // execute
    final var testUnit = EntityFlyWeight.withInsertAction(insertAction);

    // verify
    expect(testUnit.isEffectual()).isTrue();
  }

  @Test
  void testCase_withInsertAction_whenTheGivenInsertActionIsNull() {
    // execute & verify
    expectRunning(() -> EntityFlyWeight.withInsertAction(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given insert action is null.");
  }
}
