package ch.nolix.systemtest.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.systemapi.webgui.atomiccontrol.buttonapi.IButton;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class ButtonTest extends ControlTest<IButton> {
  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}
