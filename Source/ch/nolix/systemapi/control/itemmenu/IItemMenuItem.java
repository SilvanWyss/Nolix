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
  /**
   * @return true if the current {@link IItemMenuItem} belongs to a
   *         {@link IItemMenu}, false otherwise
   */
  boolean belongsToMenu();

  /**
   * @return the text of the current {@link IItemMenuItem}
   */
  String getText();

  /**
   * Sets the given parentMenu to the current {@link IItemMenuItem}.
   * 
   * @param parentMenu
   */
  void internalSetParentMenu(IItemMenu<?, ?> parentMenu);
}
