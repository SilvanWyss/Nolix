/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.singlecontainer;

import ch.nolix.systemapi.containercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {
  Control<?, ?> getStoredControl();

  ISingleContainer setControl(Control<?, ?> control);
}
