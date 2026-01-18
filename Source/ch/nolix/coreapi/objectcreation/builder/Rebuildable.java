/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.objectcreation.builder;

/**
 * A {@link Rebuildable} can rebuild itself.
 * 
 * @author Silvan Wyss
 */
public interface Rebuildable {
  /**
   * Rebuilds the current {@link Rebuildable}.
   */
  void rebuild();
}
