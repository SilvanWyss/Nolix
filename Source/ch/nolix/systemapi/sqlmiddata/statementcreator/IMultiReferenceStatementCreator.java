/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceStatementCreator {
  /**
   * @param entityId
   * @param multiReferenceColumnId
   * @return a statement to delete the multi reference entries of the entity with
   *         the given entityId for the multi reference column with the given
   *         multiReferenceColumnId
   */
  String createStatementToDeleteMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  /**
   * @param entityId
   * @param multiReferenceColumnId
   * @param referencedEntityId
   * @return a statement to delete the multi reference entry of the entity with
   *         the given entityId for the multi reference column with the given
   *         multiReferenceColumnId that references the entity with the given
   *         referencedEntityId
   */
  String createStatementToDeleteMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);

  /**
   * @param entityId
   * @param multiReferenceColumnId
   * @param referencedEntityId
   * @param referencedEntityTableId
   * @return a statement to insert a multi reference entry for the entity with the
   *         given entityId and the multi reference column with the given
   *         multiReferenceColumnId that references the entity with the given
   *         referencedEntityId of the table with the given
   *         referencedEntityTableId
   */
  String createStatementToInsertMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId,
    String referencedEntityTableId);
}
