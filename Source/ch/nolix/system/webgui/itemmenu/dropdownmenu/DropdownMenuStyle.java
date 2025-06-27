package ch.nolix.system.webgui.itemmenu.dropdownmenu;

import ch.nolix.system.webgui.itemmenu.base.AbstractItemMenuStyle;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenuStyle;

public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends AbstractItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {

  public DropdownMenuStyle() {
    initialize();
  }
}
