/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.datamodel.entityrequest;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class AbstractnessRequestableTest extends StandardTest {
  @Test
  void testCase_isConcrete_whenIsAbstract() {
    // setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(true);

    // execute
    final var result = testUnit.isConcrete();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isConcrete_whenIsConcrete() {
    // setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(false);

    // execute
    final var result = testUnit.isConcrete();

    // verify
    expect(result).isTrue();
  }
}
