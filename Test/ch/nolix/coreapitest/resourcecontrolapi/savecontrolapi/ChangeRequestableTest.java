/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapitest.resourcecontrolapi.savecontrolapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ChangeRequestableTest extends StandardTest {
  @Test
  void testCase_isChangeFree_whenIsChangeFree() {
    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(false);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isChangeFree_whenHasChanges() {
    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(true);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expect(result).isFalse();
  }
}
