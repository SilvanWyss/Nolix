/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webatomiccontrol.checkbox;

import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface ICheckbox extends IControl<ICheckbox, ICheckboxStyle> {
  ICheckbox check();

  boolean isChecked();

  ICheckbox unCheck();
}
