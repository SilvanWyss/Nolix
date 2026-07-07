/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datamodel.entityrequest;

/**
 * A {@link AbstractnessRequestable} can be asked if it is either abstract or
 * concrete.
 * 
 * @author Silvan Wyss
 */
public interface AbstractnessRequestable {
  /**
   * @return true if the current {@link AbstractnessRequestable} is abstract,
   *         false otherwise
   */
  boolean isAbstract();

  /**
   * @return true if the current {@link AbstractnessRequestable} is concrete,
   *         false otherwise
   */
  default boolean isConcrete() {
    return !isAbstract();
  }
}
