//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
final class ButtonTest extends ControlTest<IButton> {

  //method
  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}