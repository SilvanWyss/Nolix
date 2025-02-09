package ch.nolix.system.webgui.itemmenu.dropdownmenu;

import ch.nolix.system.webgui.itemmenu.ItemMenuStyle;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenuStyle;

public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends ItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {

  public DropdownMenuStyle() {
    initialize();
  }
}
