package ch.nolix.systemapi.webgui.container.tabcontainer;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableHeaderHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.IHeaderHolder;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

public interface ITabContainerTab
extends IFluentMutableHeaderHolder<IHeaderHolder>, IRootControlOwner<ITabContainerTab>, Selectable {
  boolean belongsToTabContainer();

  ITabContainer getParentTabContainer();
}
