package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ButtonHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
