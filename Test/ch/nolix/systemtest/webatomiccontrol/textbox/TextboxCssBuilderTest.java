package ch.nolix.systemtest.webatomiccontrol.textbox;

import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.textbox.TextboxCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextbox;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class TextboxCssBuilderTest extends ControlCssBuilderTest<TextboxCssBuilder, ITextbox> {
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TextboxCssBuilder createTestUnit() {
    return new TextboxCssBuilder();
  }
}
