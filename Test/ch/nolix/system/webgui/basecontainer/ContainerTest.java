package ch.nolix.system.webgui.basecontainer;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;

public abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {

  @Test
  final void testCase_constructor() {

    //execution
    final var testUnit = createTestUnit();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }
}
