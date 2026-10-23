/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceStatementCreator {
  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @return a statement to delete the multi back reference entries of the entity
   *         with the given entityId for the multi back reference column with the
   *         given multiBackReferenceColumnId
   */
  String createStatementToDeleteMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   * @return a statement to delete the multi back reference entry of the entity
   *         with the given entityId for the multi back reference column with the
   *         given multiBackReferenceColumnId that references back the entity with
   *         the given backReferencedEntityId
   */
  String createStatementToDeleteMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   * @param backReferencedEntityTableId
   * @return a statement to insert a multi back reference entry for the entity
   *         with the given entityId and the multi back reference column with the
   *         given multiBackReferenceColumnId that references back the entity with
   *         the given backReferencedEntityId of the table with the given
   *         backReferencedEntityTableId
   */
  String createStatementToInsertMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId,
    String backReferencedEntityTableId);
}
