/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseobjectexaminer;

import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link DatabaseObject}s a
 *            {@link IDatabaseObjectExaminer} is for
 */
public interface IDatabaseObjectExaminer<O extends DatabaseObject> {
  /**
   * @param databaseObject
   * @return true if the given databaseObject is new or deleted, false otherwise
   */
  boolean isNewOrDeleted(O databaseObject);

  /**
   * @param databaseObject
   * @return true if the given databaseObject is new or edited, false otherwise
   */
  boolean isNewOrEdited(O databaseObject);

  /**
   * @param databaseObject
   * @return true if the given databaseObject is new, edited or deleted, false
   *         otherwise
   */
  boolean isNewOrEditedOrDeleted(O databaseObject);

  /**
   * @param databaseObject
   * @return true if the given databaseObject is new or loaded, false otherwise
   */
  boolean isNewOrLoaded(O databaseObject);

  /**
   * @param databaseObject
   * @return true if the given databaseObject is new, loaded or edited, false
   *         otherwise
   */
  boolean isNewOrLoadedOrEdited(O databaseObject);
}
