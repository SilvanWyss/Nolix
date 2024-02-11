//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalSaveStampHolder} can have a save stamp.
 * 
 * @author Silvan Wyss
 * @date 2024-02-11
 */
public interface IOptionalSaveStampHolder {

  //method declaration
  /**
   * @return the save stamp of the current {@link IOptionalSaveStampHolder}.
   * @throws RuntimeException if the current {@link IOptionalSaveStampHolder} does
   *                          not have a save stamp.
   */
  String getSaveStamp();

  //method declaration
  /**
   * @return true if the current {@link IOptionalSaveStampHolder} has a save
   *         stamp.
   */
  boolean hasSaveStamp();
}
