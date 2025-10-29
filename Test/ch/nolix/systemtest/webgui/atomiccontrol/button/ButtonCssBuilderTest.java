package ch.nolix.systemtest.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.button.ButtonCssBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

final class ButtonCssBuilderTest extends ControlCssBuilderTest<ButtonCssBuilder, IButton> {
  @Override
  protected IButton createControl() {
    return new Button();
  }

  @Override
  protected ButtonCssBuilder createTestUnit() {
    return new ButtonCssBuilder();
  }
}
