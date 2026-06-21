/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IControlTool {
  ExtendedIterable<IControl<?, ?>> getListWithControlAndChildControlsRecursively(IControl<?, ?> control);

  ExtendedIterable<IControl<?, ?>> getListWithControlAndStructureControlsRecursively(IControl<?, ?> control);
}
