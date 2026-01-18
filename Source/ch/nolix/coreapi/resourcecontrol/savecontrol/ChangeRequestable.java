/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.resourcecontrol.savecontrol;

/**
 * A {@link ChangeRequestable} can be asked if it has uncommitted changes.
 * 
 * @author Silvan Wyss
 */
public interface ChangeRequestable {
  /**
   * @return true if the current {@link ChangeRequestable} has uncomitted changes,
   *         false otherwise.
   */
  boolean hasChanges();

  /**
   * @return true if the current {@link ChangeRequestable} does not have
   *         uncommitted changes, false otherwise.
   */
  default boolean isChangeFree() {
    return !hasChanges();
  }
}
