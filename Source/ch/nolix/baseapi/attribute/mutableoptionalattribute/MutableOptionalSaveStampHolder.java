/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalSaveStampHolder;

/**
 * A {@link MutableOptionalSaveStampHolder} is a
 * {@link OptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalSaveStampHolder extends OptionalSaveStampHolder {
  /**
   * Removes the save stamp of the current
   * {@link MutableOptionalSaveStampHolder}.
   */
  void removeSaveStamp();

  /**
   * Sets the save stamp of the current {@link MutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  void setSaveStamp(String saveStamp);
}
