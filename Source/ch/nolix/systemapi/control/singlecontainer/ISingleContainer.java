/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.singlecontainer;

import ch.nolix.systemapi.control.container.Container;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ISingleContainer extends Container<ISingleContainer, ISingleContainerStyle> {
  Control<?, ?> getStoredControl();

  ISingleContainer setControl(Control<?, ?> control);
}
