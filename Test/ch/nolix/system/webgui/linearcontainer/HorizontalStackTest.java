package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;

final class HorizontalStackTest extends ControlTest<IHorizontalStack> {

  @Override
  protected IHorizontalStack createTestUnit() {
    return new HorizontalStack();
  }
}
