/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IControlAnalyser {
  /**
   * @param firstControl
   * @param secondControl
   * @return true if the given firstControl contains the given secondControl
   *         recursively, false otherwise
   */
  boolean firstControlContainsSecondControl(Control<?, ?> firstControl, Control<?, ?> secondControl);
}
