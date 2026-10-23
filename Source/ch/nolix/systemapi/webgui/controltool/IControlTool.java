/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IControlTool {
  /**
   * @param control
   * @return a list with the given control and the child {@link Control}s of the
   *         given control recursively
   */
  ExtendedIterable<Control<?, ?>> getListWithControlAndChildControlsRecursively(Control<?, ?> control);

  /**
   * @param control
   * @return a list with the given control and the structure {@link Control}s of
   *         the given control recursively
   */
  ExtendedIterable<Control<?, ?>> getListWithControlAndStructureControlsRecursively(Control<?, ?> control);
}
