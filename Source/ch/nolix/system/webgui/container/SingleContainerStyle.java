//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;

//class
public final class SingleContainerStyle
    extends ControlStyle<ISingleContainerStyle>
    implements ISingleContainerStyle {

  //constructor
  public SingleContainerStyle() {
    initialize();
  }
}
