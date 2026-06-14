/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IControlTool {
  IWellOrderContainer<IControl<?, ?>> getListWithControlAndChildControlsRecursively(IControl<?, ?> control);

  IWellOrderContainer<IControl<?, ?>> getListWithControlAndStructureControlsRecursively(IControl<?, ?> control);
}
