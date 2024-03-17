//package declaration
package ch.nolix.systemtest.webguitest.containertest;

import org.junit.jupiter.api.Test;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
public abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {

  //method
  @Test
  public final void testCase_constructor() {

    //execution
    final var testUnit = createTestUnit();

    //verification
    expect(testUnit.isEmpty());
  }
}
