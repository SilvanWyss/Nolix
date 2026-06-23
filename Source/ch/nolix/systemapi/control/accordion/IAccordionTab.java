/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.accordion;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableHeaderHolder;
import ch.nolix.systemapi.gui.selection.Selectable;
import ch.nolix.systemapi.webgui.main.IRootControlOwner;

/**
 * @author Silvan Wyss
 */
public interface IAccordionTab
extends FluentMutableHeaderHolder<IAccordionTab>, IRootControlOwner<IAccordionTab>, Selectable {
  boolean belongsToAccordion();

  IAccordion getParentAccordion();
}
