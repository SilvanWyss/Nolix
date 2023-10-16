//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploaderStyle;

//class
public final class UploaderStyle extends ControlStyle<IUploaderStyle> implements IUploaderStyle {

  //constructor
  public UploaderStyle() {
    initialize();
  }
}
