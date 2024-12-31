package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.atomiccontrol.textbox.TextboxHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

final class TextboxHtmlBuilderTest extends ControlHtmlBuilderTest<TextboxHtmlBuilder, ITextbox> {

  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  @Override
  protected TextboxHtmlBuilder createTestUnit() {
    return new TextboxHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input value=\"\" />";
  }
}
