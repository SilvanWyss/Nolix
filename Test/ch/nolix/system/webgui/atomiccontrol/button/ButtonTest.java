package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;

final class ButtonTest extends ControlTest<IButton> {

  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}
