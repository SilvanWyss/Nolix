/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.tabcontainer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableHeaderHolder;
import ch.nolix.systemapi.element.mutableelement.MutableElement;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.RootControlManager;

/**
 * @author Silvan Wyss
 */
public interface ITabContainerTab
extends FluentMutableHeaderHolder<ITabContainerTab>, MutableElement, RootControlManager<ITabContainerTab>, Selectable {
  boolean belongsToTabContainer();

  ITabContainer getStoredParentTabContainer();

  void internalsetParentTabContainer(final ITabContainer tabContainer);
}
