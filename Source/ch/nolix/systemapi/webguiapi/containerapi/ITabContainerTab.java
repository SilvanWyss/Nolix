package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableHeaderHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IHeaderHolder;
import ch.nolix.systemapi.guiapi.selectionapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

public interface ITabContainerTab
extends IFluentMutableHeaderHolder<IHeaderHolder>, IRootControlOwner<ITabContainerTab>, Selectable {

  boolean belongsToTabContainer();

  ITabContainer getParentTabContainer();
}
