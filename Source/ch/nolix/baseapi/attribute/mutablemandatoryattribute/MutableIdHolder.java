/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;

/**
 * A {@link MutableIdHolder} is a {@link IdHolder} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableIdHolder extends IdHolder {
  /**
   * Sets the id of the current {@link MutableIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null or blank
   */
  void setId(String id);
}
