package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableHeaderHolder;
import ch.nolix.systemapi.guiapi.selectionapi.Selectable;
import ch.nolix.systemapi.webguiapi.mainapi.IRootControlOwner;

public interface IAccordionTab
extends IFluentMutableHeaderHolder<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {

  boolean belongsToAccordion();

  IAccordion getParentAccordion();
}
