/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ISaveStampHolder;

/**
 * A {@link IMutableSaveStampHolder} is a {@link ISaveStampHolder} whose save
 * stamp can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableSaveStampHolder extends ISaveStampHolder {
  /**
   * Sets the save stamp of the current {@link IMutableSaveStampHolder}.
   * 
   * @param saveStamp
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  void setSaveStamp(String saveStamp);
}
