/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.tabcontainer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableHeaderHolder;
import ch.nolix.systemapi.element.mutableelement.MutableElement;
import ch.nolix.systemapi.gui.guicontrol.Selectable;
import ch.nolix.systemapi.webgui.main.RootControlManager;

/**
 * @author Silvan Wyss
 */
public interface ITabContainerTab
extends FluentMutableHeaderHolder<ITabContainerTab>, MutableElement, RootControlManager<ITabContainerTab>, Selectable {
  /**
   * @return true if the current {@link ITabContainerTab} belongs to a
   *         {@link ITabContainer}, false otherwise
   */
  boolean belongsToTabContainer();

  /**
   * @return the parent {@link ITabContainer} of the current
   *         {@link ITabContainerTab}
   * @throws RuntimeException if the current {@link ITabContainerTab} does not
   *                          belong to a {@link ITabContainer}
   */
  ITabContainer getStoredParentTabContainer();

  /**
   * Sets the given tabContainer as parent {@link ITabContainer} to the current
   * {@link ITabContainerTab}.
   * 
   * @param tabContainer
   */
  void internalsetParentTabContainer(ITabContainer tabContainer);
}
