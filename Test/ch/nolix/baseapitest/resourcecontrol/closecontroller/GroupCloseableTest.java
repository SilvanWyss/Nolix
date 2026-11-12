/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.resourcecontrol.closecontroller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 */
final class GroupCloseableTest extends StandardTest {
  @Test
  void testCase_close() {
    // setup
    final var closeControllerMock = Mockito.mock(ICloseController.class);

    try (final var testUnit = Mockito.mock(GroupCloseable.class)) {
      Mockito.doCallRealMethod().when(testUnit).close();
      Mockito.when(testUnit.getStoredCloseController()).thenReturn(closeControllerMock);

      // execute
      testUnit.close(); // NOSONAR: This test case tests the close method.

      // verify
      Mockito.verify(closeControllerMock).close();
    }
  }

  @Test
  void testCase_createCloseDependencyTo() {
    // setup
    final var element = Mockito.mock(GroupCloseable.class);
    final var closeControllerMock = Mockito.mock(ICloseController.class);
    final var testUnit = Mockito.mock(GroupCloseable.class);
    Mockito.doCallRealMethod().when(testUnit).createCloseDependencyTo(ArgumentMatchers.any());
    Mockito.when(testUnit.getStoredCloseController()).thenReturn(closeControllerMock);

    // execute
    testUnit.createCloseDependencyTo(element);

    // verify
    Mockito.verify(closeControllerMock).createCloseDependencyTo(element);
  }

  @Test
  void testCase_isClosed_whenCloseControllerHasClosed() {
    // setup
    final var closeControllerMock = Mockito.mock(ICloseController.class);
    Mockito.when(closeControllerMock.hasClosed()).thenReturn(true);
    final var testUnit = Mockito.mock(GroupCloseable.class);
    Mockito.when(testUnit.isClosed()).thenCallRealMethod();
    Mockito.when(testUnit.getStoredCloseController()).thenReturn(closeControllerMock);

    // execute
    final var result = testUnit.isClosed();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isClosed_whenCloseControllerHasNotClosed() {
    // setup
    final var closeControllerMock = Mockito.mock(ICloseController.class);
    Mockito.when(closeControllerMock.hasClosed()).thenReturn(false);
    final var testUnit = Mockito.mock(GroupCloseable.class);
    Mockito.when(testUnit.isClosed()).thenCallRealMethod();
    Mockito.when(testUnit.getStoredCloseController()).thenReturn(closeControllerMock);

    // execute
    final var result = testUnit.isClosed();

    // verify
    expect(result).isFalse();
  }
}
