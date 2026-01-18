/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.selection;

/**
 * @author Silvan Wyss
 */
public interface Selectable extends SelectionRequestable {
  void select();

  void unselect();
}
