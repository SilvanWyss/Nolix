/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.dropdownmenu;

import ch.nolix.system.control.itemmenu.AbstractItemMenuStyle;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenuStyle;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuStyle // NOSONAR: A DropdownMenuStyle is a ItemMenuStyle.
extends AbstractItemMenuStyle<IDropdownMenuStyle>
implements IDropdownMenuStyle {
  public DropdownMenuStyle() {
    initialize();
  }
}
