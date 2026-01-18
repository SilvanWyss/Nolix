/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapitest.datamodelapi.entityrequestapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class AbstractnessRequestableTest extends StandardTest {
  @Test
  void testCase_isConcrete_whenIsAbstract() {
    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(true);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isConcrete_whenIsConcrete() {
    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(false);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expect(result).isTrue();
  }
}
