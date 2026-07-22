/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.resourcecontrol.savecontrol;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ChangeRequestableTest extends StandardTest {
  @Test
  void testCase_isChangeFree_whenIsChangeFree() {
    // setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(false);

   // execute
    final var result = testUnit.isChangeFree();

   // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isChangeFree_whenHasChanges() {
    // setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(true);

   // execute
    final var result = testUnit.isChangeFree();

   // verify
    expect(result).isFalse();
  }
}
