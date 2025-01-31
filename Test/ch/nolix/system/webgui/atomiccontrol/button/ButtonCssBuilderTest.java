package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.basecontroltool.ControlCssBuilderTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;

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
