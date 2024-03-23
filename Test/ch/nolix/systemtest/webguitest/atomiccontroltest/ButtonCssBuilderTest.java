//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ButtonCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlCssBuilderTest;

//class
final class ButtonCssBuilderTest extends ControlCssBuilderTest<ButtonCssBuilder, IButton> {

  //method
  @Override
  protected IButton createControl() {
    return new Button();
  }

  //method
  @Override
  protected ButtonCssBuilder createTestUnit() {
    return new ButtonCssBuilder();
  }
}
