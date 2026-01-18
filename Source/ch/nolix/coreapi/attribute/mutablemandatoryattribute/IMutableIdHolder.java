/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;

/**
 * A {@link IMutableIdHolder} is a {@link IIdHolder} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
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
