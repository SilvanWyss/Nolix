/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.accordion;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableHeaderHolder;
import ch.nolix.systemapi.gui.guicontrol.Selectable;
import ch.nolix.systemapi.webgui.main.RootControlManager;

/**
 * @author Silvan Wyss
 */
public interface IAccordionTab
extends FluentMutableHeaderHolder<IAccordionTab>, RootControlManager<IAccordionTab>, Selectable {
  boolean belongsToAccordion();

  IAccordion getParentAccordion();
}
