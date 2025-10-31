package ch.nolix.systemtest.webatomiccontrol.button;

import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class ButtonTest extends ControlTest<IButton> {
  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}
