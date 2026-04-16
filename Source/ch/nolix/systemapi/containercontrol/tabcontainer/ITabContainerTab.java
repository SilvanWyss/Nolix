/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.tabcontainer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.IFluentMutableHeaderHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.IHeaderHolder;
import ch.nolix.systemapi.element.mutableelement.IMutableElement;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

/**
 * @author Silvan Wyss
 */
public interface ITabContainerTab
extends IFluentMutableHeaderHolder<IHeaderHolder>, IMutableElement, IRootControlOwner<ITabContainerTab>, Selectable {
  boolean belongsToTabContainer();

  ITabContainer getStoredParentTabContainer();

  void setParentTabContainer(final ITabContainer tabContainer);
}
