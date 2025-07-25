package ch.nolix.systemtest.webgui.basecontainer;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.webgui.basecontainer.IContainer;
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
