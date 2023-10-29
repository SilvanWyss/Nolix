//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableHeaderHolder;
import ch.nolix.systemapi.guiapi.processapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

//interface
public interface IAccordionTab
extends IFluentMutableHeaderHolder<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {

  //method declaration
  boolean belongsToAccordion();

  //method declaration
  IAccordion getParentAccordion();
}
