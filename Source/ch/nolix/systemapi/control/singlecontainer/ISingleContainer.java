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
  /**
   * @return the {@link Control} of the current {@link ISingleContainer}
   * @throws RuntimeException if the current {@link ISingleContainer} is empty
   */
  Control<?, ?> getStoredControl();

  /**
   * Sets the given control to the current {@link ISingleContainer}.
   * 
   * @param control
   * @return the current {@link ISingleContainer}
   */
  ISingleContainer setControl(Control<?, ?> control);
}
