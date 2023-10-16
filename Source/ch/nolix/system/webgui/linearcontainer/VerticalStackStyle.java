//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;

//class
public final class VerticalStackStyle //NOSONAR: A VerticalStackStyle is a LinearContainerStyle.
    extends LinearContainerStyle<IVerticalStackStyle>
    implements IVerticalStackStyle {

  //constructor
  public VerticalStackStyle() {
    initialize();
  }
}
