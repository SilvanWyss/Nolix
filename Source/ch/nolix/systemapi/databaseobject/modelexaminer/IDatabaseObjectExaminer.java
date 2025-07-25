package ch.nolix.systemapi.databaseobject.modelexaminer;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IDatabaseObjectExaminer {

  boolean isNewOrDeleted(IDatabaseObject databaseObject);

  boolean isNewOrEdited(IDatabaseObject databaseObject);

  boolean isNewOrEditedOrDeleted(IDatabaseObject databaseObject);

  boolean isNewOrLoaded(IDatabaseObject databaseObject);

  boolean isNewOrLoadedOrEdited(IDatabaseObject databaseObject);
}
