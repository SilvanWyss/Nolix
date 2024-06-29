//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;

//interface
/**
 * A {@link IMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 */
public interface IMutableOptionalSaveStampHolder extends IOptionalSaveStampHolder {

  //method declaration
  /**
   * Removes the save stamp of the current
   * {@link IMutableOptionalSaveStampHolder}.
   */
  void removeSaveStamp();

  //method declaration
  /**
   * Sets the save stamp of the current {@link IMutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  void setSaveStamp(String saveStamp);
}
