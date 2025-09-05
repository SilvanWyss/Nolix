package ch.nolix.coreapi.resourcecontrol.savecontrol;

import ch.nolix.coreapi.resourcecontrol.closecontroller.Closeable;

/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @version 2018-04-09
 */
public interface IChangeSaver extends Closeable, ChangeRequestable {
  /**
   * Saves the changes of the current {@link IChangeSaver}.
   */
  void saveChanges();
}
