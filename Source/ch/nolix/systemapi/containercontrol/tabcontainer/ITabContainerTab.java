/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.tabcontainer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableHeaderHolder;
import ch.nolix.systemapi.element.mutableelement.IMutableElement;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

/**
 * @author Silvan Wyss
 */
public interface ITabContainerTab
extends FluentMutableHeaderHolder<ITabContainerTab>, IMutableElement, IRootControlOwner<ITabContainerTab>, Selectable {
  boolean belongsToTabContainer();

  ITabContainer getStoredParentTabContainer();

  void internalsetParentTabContainer(final ITabContainer tabContainer);
}
