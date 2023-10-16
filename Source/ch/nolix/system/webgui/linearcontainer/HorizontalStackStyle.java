//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;

//class
public final class HorizontalStackStyle //NOSONAR: A HorizontalStackStyle is a LinearContainerStyle.
    extends LinearContainerStyle<IHorizontalStackStyle>
    implements IHorizontalStackStyle {

  //constructor
  public HorizontalStackStyle() {
    initialize();
  }
}
