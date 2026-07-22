/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.container;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.control.container.Container;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link Container}s if a {@link ContainerTest}.
 */
public abstract class ContainerTest<C extends Container<C, ?>> extends ControlTest<C> {
  @Test
  final void testCase_constructor() {
   // execute
    final var testUnit = createTestUnit();

   // verify
    expect(testUnit.isEmpty()).isTrue();
  }
}
