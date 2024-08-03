//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.TextboxHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class TextboxHtmlBuilderTest extends ControlHtmlBuilderTest<TextboxHtmlBuilder, ITextbox> {

  //method
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  //method
  @Override
  protected TextboxHtmlBuilder createTestUnit() {
    return new TextboxHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input value=\"\" />";
  }
}
