/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IControlTool {
  IContainer<IControl<?, ?>> getListWithControlAndChildControlsRecursively(IControl<?, ?> control);
}
