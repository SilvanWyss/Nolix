package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.system.webgui.basecontroltool.ControlHtmlBuilderTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;

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
