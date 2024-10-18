package ch.nolix.system.webgui.itemmenu;

import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends ItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {

  public DropdownMenuStyle() {
    initialize();
  }
}
