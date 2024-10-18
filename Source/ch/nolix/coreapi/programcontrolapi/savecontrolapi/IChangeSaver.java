package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.Closeable;

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
