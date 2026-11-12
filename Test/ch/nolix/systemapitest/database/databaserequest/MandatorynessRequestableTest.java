/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.database.databaserequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.database.databaserequest.MandatorynessRequestable;

/**
 * @author Silvan Wyss
 */
final class MandatorynessRequestableTest extends StandardTest {
  @Test
  void testCase_isOptional_whenIsMandatory() {
    // setup
    final var testUnit = Mockito.mock(MandatorynessRequestable.class);
    Mockito.when(testUnit.isOptional()).thenCallRealMethod();
    Mockito.when(testUnit.isMandatory()).thenReturn(true);

    // execute
    final var result = testUnit.isOptional();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isOptional_whenIsNotMandatory() {
    // setup
    final var testUnit = Mockito.mock(MandatorynessRequestable.class);
    Mockito.when(testUnit.isOptional()).thenCallRealMethod();
    Mockito.when(testUnit.isMandatory()).thenReturn(false);

    // execute
    final var result = testUnit.isOptional();

    // verify
    expect(result).isTrue();
  }
}
