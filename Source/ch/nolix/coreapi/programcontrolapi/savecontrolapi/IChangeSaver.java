//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.Closeable;

//interface
/**
 * A {@link IChangeSaver} can save its changes.
 * 
 * @author Silvan Wyss
 * @date 2018-04-09
 */
public interface IChangeSaver extends Closeable, ChangeRequestable {

  //method declaration
  /**
   * Saves the changes of the current {@link IChangeSaver}.
   */
  void saveChanges();
}
