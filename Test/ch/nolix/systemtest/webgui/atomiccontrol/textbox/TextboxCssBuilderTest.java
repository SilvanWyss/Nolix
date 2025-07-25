package ch.nolix.systemtest.webgui.atomiccontrol.textbox;

import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.atomiccontrol.textbox.TextboxCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

final class TextboxCssBuilderTest extends ControlCssBuilderTest<TextboxCssBuilder, ITextbox> {

  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  @Override
  protected TextboxCssBuilder createTestUnit() {
    return new TextboxCssBuilder();
  }
}
