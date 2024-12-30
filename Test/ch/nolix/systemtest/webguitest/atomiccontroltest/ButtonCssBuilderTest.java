package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.button.ButtonCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlCssBuilderTest;

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
