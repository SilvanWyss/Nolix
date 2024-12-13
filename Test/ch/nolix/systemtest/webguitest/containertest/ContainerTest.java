package ch.nolix.systemtest.webguitest.containertest;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {

  @Test
  final void testCase_constructor() {

    //execution
    final var testUnit = createTestUnit();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }
}
