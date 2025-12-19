package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ISaveStampHolder;

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
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  void setSaveStamp(String saveStamp);
}
