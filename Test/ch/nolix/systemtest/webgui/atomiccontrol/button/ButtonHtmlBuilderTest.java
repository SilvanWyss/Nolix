package ch.nolix.systemtest.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.button.ButtonHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class ButtonHtmlBuilderTest extends ControlHtmlBuilderTest<ButtonHtmlBuilder, IButton> {

  @Override
  protected IButton createControl() {
    return new Button();
  }

  @Override
  protected ButtonHtmlBuilder createTestUnit() {
    return new ButtonHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<button>-</button>";
  }
}
