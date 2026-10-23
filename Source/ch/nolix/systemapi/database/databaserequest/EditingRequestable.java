/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaserequest;

/**
 * @author Silvan Wyss
 */
public interface EditingRequestable {
  /**
   * @return true if the current {@link EditingRequestable} is edited, false
   *         otherwise
   */
  boolean isEdited();
}
