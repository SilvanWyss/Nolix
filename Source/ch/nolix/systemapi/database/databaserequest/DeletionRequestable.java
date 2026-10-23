/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaserequest;

/**
 * @author Silvan Wyss
 */
public interface DeletionRequestable {
  /**
   * @return true if the current {@link DeletionRequestable} is deleted, false
   *         otherwise
   */
  boolean isDeleted();
}
