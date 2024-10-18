package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.TextboxCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlCssBuilderTest;

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
