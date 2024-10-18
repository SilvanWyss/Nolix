package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ISaveStampHolder;

/**
 * A {@link IMutableSaveStampHolder} is a {@link ISaveStampHolder} whose save
 * stamp can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
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
