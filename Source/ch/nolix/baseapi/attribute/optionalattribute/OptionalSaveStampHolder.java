/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalSaveStampHolder} can have a save stamp.
 * 
 * @author Silvan Wyss
 */
public interface OptionalSaveStampHolder {
  /**
   * @return the save stamp of the current {@link OptionalSaveStampHolder}
   * @throws RuntimeException if the current {@link OptionalSaveStampHolder} does
   *                          not have a save stamp
   */
  String getSaveStamp();

  /**
   * @return true if the current {@link OptionalSaveStampHolder} has a save
   *         stamp, false otherwise
   */
  boolean hasSaveStamp();
}
