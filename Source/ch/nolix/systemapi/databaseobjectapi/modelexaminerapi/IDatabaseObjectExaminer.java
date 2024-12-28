package ch.nolix.systemapi.databaseobjectapi.modelexaminerapi;

import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IDatabaseObjectExaminer {

  boolean isNewOrDeleted(IDatabaseObject databaseObject);

  boolean isNewOrEdited(IDatabaseObject databaseObject);

  boolean isNewOrLoaded(IDatabaseObject databaseObject);

  boolean isNewOrLoadedOrEdited(IDatabaseObject databaseObject);
}
