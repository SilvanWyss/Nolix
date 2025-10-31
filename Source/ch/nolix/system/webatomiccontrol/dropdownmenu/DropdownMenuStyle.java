package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.system.webgui.itemmenu.base.AbstractItemMenuStyle;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;

public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends AbstractItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {
  public DropdownMenuStyle() {
    initialize();
  }
}
