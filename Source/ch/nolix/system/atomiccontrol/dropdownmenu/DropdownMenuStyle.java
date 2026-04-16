/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.dropdownmenu;

import ch.nolix.system.atomiccontrol.itemmenu.AbstractItemMenuStyle;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenuStyle;

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
