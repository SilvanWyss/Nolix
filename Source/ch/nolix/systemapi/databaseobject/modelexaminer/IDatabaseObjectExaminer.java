/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.databaseobject.modelexaminer;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseObjectExaminer {
  boolean isNewOrDeleted(IDatabaseObject databaseObject);

  boolean isNewOrEdited(IDatabaseObject databaseObject);

  boolean isNewOrEditedOrDeleted(IDatabaseObject databaseObject);

  boolean isNewOrLoaded(IDatabaseObject databaseObject);

  boolean isNewOrLoadedOrEdited(IDatabaseObject databaseObject);
}
