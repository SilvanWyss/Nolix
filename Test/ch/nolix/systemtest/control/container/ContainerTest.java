/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.container;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.control.container.IContainer;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the {@link IContainer}s if a {@link ContainerTest}.
 */
public abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {
  @Test
  final void testCase_constructor() {
    //execution
    final var testUnit = createTestUnit();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }
}
