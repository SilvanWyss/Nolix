//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;

//class
public final class LabelStyle extends ControlStyle<ILabelStyle> implements ILabelStyle {

  //constructor
  public LabelStyle() {
    initialize();
  }
}
