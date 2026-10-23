/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.checkbox;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ICheckbox extends Control<ICheckbox, ICheckboxStyle> {
  /**
   * Checks the current {@link ICheckbox}.
   * 
   * @return the current {@link ICheckbox}
   */
  ICheckbox check();

  /**
   * @return true if the current {@link ICheckbox} is checked, false otherwise
   */
  boolean isChecked();

  /**
   * Unchecks the current {@link ICheckbox}.
   * 
   * @return the current {@link ICheckbox}
   */
  ICheckbox unCheck();
}
