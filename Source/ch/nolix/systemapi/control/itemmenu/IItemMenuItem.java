/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.itemmenu;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;
import ch.nolix.baseapi.generalstate.staterequest.BlanknessRequestable;
import ch.nolix.systemapi.element.mutableelement.MutableElement;
import ch.nolix.systemapi.gui.guicontrol.Selectable;

/**
 * @author Silvan Wyss
 * @param <I> the type of a {@link IItemMenuItem}.
 */
public interface IItemMenuItem<I extends IItemMenuItem<I>>
extends BlanknessRequestable, IdHolder, MutableElement, Selectable {
  boolean belongsToMenu();

  String getText();

  void internalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
