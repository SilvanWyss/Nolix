package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;

/**
 * A {@link IMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 */
public interface IMutableOptionalSaveStampHolder extends IOptionalSaveStampHolder {

  /**
   * Removes the save stamp of the current
   * {@link IMutableOptionalSaveStampHolder}.
   */
  void removeSaveStamp();

  /**
   * Sets the save stamp of the current {@link IMutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  void setSaveStamp(String saveStamp);
}
