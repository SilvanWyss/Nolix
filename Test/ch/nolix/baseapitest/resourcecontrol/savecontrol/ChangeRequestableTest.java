/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.resourcecontrol.savecontrol;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.ChangeRequestable;

/**
 * @author Silvan Wyss
 */
final class ChangeRequestableTest extends StandardTest {
  @Test
  void testCase_isChangeFree_whenIsChangeFree() {
    // setup
    final var testUnit = Mockito.mock(ChangeRequestable.class);
    when(testUnit.isChangeFree()).thenCallRealMethod();
    when(testUnit.hasChanges()).thenReturn(false);

    // execute
    final var result = testUnit.isChangeFree();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isChangeFree_whenHasChanges() {
    // setup
    final var testUnit = Mockito.mock(ChangeRequestable.class);
    when(testUnit.isChangeFree()).thenCallRealMethod();
    when(testUnit.hasChanges()).thenReturn(true);

    // execute
    final var result = testUnit.isChangeFree();

    // verify
    expect(result).isFalse();
  }
}
