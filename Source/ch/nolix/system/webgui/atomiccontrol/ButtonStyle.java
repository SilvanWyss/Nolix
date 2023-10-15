//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButtonStyle;

//class
public final class ButtonStyle extends ControlStyle<IButtonStyle> implements IButtonStyle {

  // constructor
  public ButtonStyle() {
    initialize();
  }
}
