/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.savecontrol;

import ch.nolix.baseapi.resourcecontrol.closecontroller.Closeable;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.ChangeRequestable;

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
