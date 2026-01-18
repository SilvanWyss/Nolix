/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.objectcomposition.hierarchy;

/**
 * A {@link RootRequestable} can be asked if it is either a root object or a
 * child object.
 * 
 * @author Silvan Wyss
 */
public interface RootRequestable {
  /**
   * @return true if the current {@link RootRequestable} is a child object, false
   *         otherwise.
   */
  default boolean isChild() {
    return !isRoot();
  }

  /**
   * @return true if the current {@link RootRequestable} is a root object, false
   *         otherwise.
   */
  boolean isRoot();
}
