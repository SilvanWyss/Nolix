package ch.nolix.systemtest.webgui.linearcontainer;

import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webgui.linearcontainer.IHorizontalStack;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class HorizontalStackTest extends ControlTest<IHorizontalStack> {
  @Override
  protected IHorizontalStack createTestUnit() {
    return new HorizontalStack();
  }
}
