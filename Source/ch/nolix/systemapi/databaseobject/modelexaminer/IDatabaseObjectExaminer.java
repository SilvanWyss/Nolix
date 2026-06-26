/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.databaseobject.modelexaminer;

import ch.nolix.systemapi.databaseobject.model.DatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseObjectExaminer {
  boolean isNewOrDeleted(DatabaseObject databaseObject);

  boolean isNewOrEdited(DatabaseObject databaseObject);

  boolean isNewOrEditedOrDeleted(DatabaseObject databaseObject);

  boolean isNewOrLoaded(DatabaseObject databaseObject);

  boolean isNewOrLoadedOrEdited(DatabaseObject databaseObject);
}
