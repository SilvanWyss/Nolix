package ch.nolix.systemtest.webcontainercontrol.container;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.webcontainercontrol.container.IContainer;
import ch.nolix.systemtest.webgui.main.ControlTest;

public abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {
  @Test
  final void testCase_constructor() {
    //execution
    final var testUnit = createTestUnit();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }
}
