/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webcontainercontrol.accordion;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableHeaderHolder;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

/**
 * @author Silvan Wyss
 */
public interface IAccordionTab
extends IFluentMutableHeaderHolder<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {
  boolean belongsToAccordion();

  IAccordion getParentAccordion();
}
