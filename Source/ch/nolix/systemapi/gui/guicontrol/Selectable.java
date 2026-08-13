/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guicontrol;

import ch.nolix.systemapi.gui.guirequest.SelectionRequestable;

/**
 * @author Silvan Wyss
 */
public interface Selectable extends SelectionRequestable {
  void select();

  void unselect();
}
