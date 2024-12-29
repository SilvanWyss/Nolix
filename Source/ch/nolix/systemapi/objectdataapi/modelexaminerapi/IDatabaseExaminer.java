package ch.nolix.systemapi.objectdataapi.modelexaminerapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IDatabaseExaminer {

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

  @Deprecated
  boolean hasChanges(IDatabase database);
}
