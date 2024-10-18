package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;

/**
 * A {@link IMutableIdHolder} is a {@link IIdHolder} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableIdHolder extends IIdHolder {

  /**
   * Sets the id of the current {@link IMutableIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
