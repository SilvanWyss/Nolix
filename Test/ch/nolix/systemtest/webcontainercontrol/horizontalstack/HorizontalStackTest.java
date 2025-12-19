package ch.nolix.systemtest.webcontainercontrol.horizontalstack;

import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class HorizontalStackTest extends ControlTest<IHorizontalStack> {
  @Override
  protected IHorizontalStack createTestUnit() {
    return new HorizontalStack();
  }
}
