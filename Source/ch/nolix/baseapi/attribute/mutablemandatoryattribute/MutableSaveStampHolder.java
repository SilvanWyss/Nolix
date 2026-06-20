/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.SaveStampHolder;

/**
 * A {@link MutableSaveStampHolder} is a {@link SaveStampHolder} whose save
 * stamp can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableSaveStampHolder extends SaveStampHolder {
  /**
   * Sets the save stamp of the current {@link MutableSaveStampHolder}.
   * 
   * @param saveStamp
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  void setSaveStamp(String saveStamp);
}
