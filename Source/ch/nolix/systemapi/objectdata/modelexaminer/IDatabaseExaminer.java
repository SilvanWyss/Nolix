package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IDatabaseExaminer extends IDatabaseObjectExaminer {
  /**
   * @param database
   * @return true if all new and edited mandatory fields of the given database are
   *         set, false otherwise.
   */
  boolean allNewAndEditedMandatoryFieldsAreSet(IDatabase database);

  /**
   * @param database
   * @return true if the given database can save its chances.
   */
  boolean canSaveChanges(IDatabase database);
}
