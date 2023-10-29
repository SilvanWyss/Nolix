//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

//class
public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends ItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {

  //constructor
  public DropdownMenuStyle() {
    initialize();
  }
}
