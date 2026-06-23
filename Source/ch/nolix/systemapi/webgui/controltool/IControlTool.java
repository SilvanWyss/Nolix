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
  ExtendedIterable<Control<?, ?>> getListWithControlAndChildControlsRecursively(Control<?, ?> control);

  ExtendedIterable<Control<?, ?>> getListWithControlAndStructureControlsRecursively(Control<?, ?> control);
}
