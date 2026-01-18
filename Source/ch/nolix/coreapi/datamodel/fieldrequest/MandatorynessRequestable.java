/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.datamodel.fieldrequest;

/**
 * A {@link MandatorynessRequestable} can be asked if it is mandatory.
 * 
 * @author Silvan Wyss
 */
public interface MandatorynessRequestable {
  /**
   * @return true if the current {@link MandatorynessRequestable} is mandatory,
   *         false otherwise.
   */
  boolean isMandatory();

  /**
   * @return true if the current {@link MandatorynessRequestable} is optional,
   *         false otherwise.
   */
  default boolean isOptional() {
    return !isMandatory();
  }
}
