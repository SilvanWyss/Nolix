package ch.nolix.systemapi.webgui.container.accordion;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableHeaderHolder;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

public interface IAccordionTab
extends IFluentMutableHeaderHolder<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {
  boolean belongsToAccordion();

  IAccordion getParentAccordion();
}
