/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.system.webatomiccontrol.itemmenu.AbstractItemMenuStyle;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenuStyle;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuStyle //NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends AbstractItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {
  public DropdownMenuStyle() {
    initialize();
  }
}
