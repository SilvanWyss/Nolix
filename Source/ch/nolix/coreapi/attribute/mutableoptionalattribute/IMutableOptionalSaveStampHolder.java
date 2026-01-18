/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalSaveStampHolder;

/**
 * A {@link IMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
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
