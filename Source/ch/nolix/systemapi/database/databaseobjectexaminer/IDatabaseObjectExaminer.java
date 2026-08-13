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
  boolean isNewOrDeleted(O databaseObject);

  boolean isNewOrEdited(O databaseObject);

  boolean isNewOrEditedOrDeleted(O databaseObject);

  boolean isNewOrLoaded(O databaseObject);

  boolean isNewOrLoadedOrEdited(O databaseObject);
}
