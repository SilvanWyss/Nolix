/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * A {@link MutableNameHolder} is a {@link NameHolder} whose name can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableNameHolder extends NameHolder {
  /**
   * Sets the name of the current {@link MutableNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null or blank
   */
  void setName(String name);
}
