package ch.nolix.systemtest.webatomiccontrol.button;

import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.button.ButtonHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
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
