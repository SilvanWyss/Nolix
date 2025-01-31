package ch.nolix.system.webgui.atomiccontrol.textbox;

import ch.nolix.system.webgui.basecontroltool.ControlCssBuilderTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;

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
