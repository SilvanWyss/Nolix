//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.TextboxCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlCssBuilderTest;

//class
public final class TextboxCssBuilderTest extends ControlCssBuilderTest<TextboxCssBuilder, ITextbox> {

  //method
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  //method
  @Override
  protected TextboxCssBuilder createTestUnit() {
    return new TextboxCssBuilder();
  }
}
