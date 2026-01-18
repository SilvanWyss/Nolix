package ch.nolix.systemtest.webatomiccontrol.button;

import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.button.ButtonHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ButtonHtmlBuilderTest extends ControlHtmlBuilderTest<ButtonHtmlBuilder, IButton> {
  @Override
  protected IButton createControl() {
    return new Button();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ButtonHtmlBuilder createTestUnit() {
    return new ButtonHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<button>-</button>";
  }
}
