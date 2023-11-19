//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableHeaderHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IHeaderHolder;
import ch.nolix.systemapi.guiapi.selectionapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

//interface
public interface ITabContainerTab
extends IFluentMutableHeaderHolder<IHeaderHolder>, IRootControlOwner<ITabContainerTab>, Selectable {

  //method declaration
  boolean belongsToTabContainer();

  //method declaration
  ITabContainer getParentTabContainer();
}
