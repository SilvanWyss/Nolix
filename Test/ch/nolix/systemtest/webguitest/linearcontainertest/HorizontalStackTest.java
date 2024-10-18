package ch.nolix.systemtest.webguitest.linearcontainertest;

import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class HorizontalStackTest extends ControlTest<IHorizontalStack> {

  @Override
  protected IHorizontalStack createTestUnit() {
    return new HorizontalStack();
  }
}
