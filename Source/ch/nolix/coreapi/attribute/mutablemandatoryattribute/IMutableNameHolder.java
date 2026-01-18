/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * A {@link IMutableNameHolder} is a {@link INameHolder} whose name can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableNameHolder extends INameHolder {
  /**
   * Sets the name of the current {@link IMutableNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  void setName(String name);
}
