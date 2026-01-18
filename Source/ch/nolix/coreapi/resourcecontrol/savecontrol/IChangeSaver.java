/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.resourcecontrol.savecontrol;

import ch.nolix.coreapi.resourcecontrol.closecontroller.Closeable;

/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 */
public interface IChangeSaver extends Closeable, ChangeRequestable {
  /**
   * Saves the changes of the current {@link IChangeSaver}.
   */
  void saveChanges();
}
