package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class ButtonTest extends ControlTest<IButton> {

  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}
