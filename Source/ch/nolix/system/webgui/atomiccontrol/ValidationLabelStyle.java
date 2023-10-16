//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelStyle;

//class
public final class ValidationLabelStyle
    extends ControlStyle<IValidationLabelStyle>
    implements IValidationLabelStyle {

  //constructor
  public ValidationLabelStyle() {
    initialize();
  }
}
