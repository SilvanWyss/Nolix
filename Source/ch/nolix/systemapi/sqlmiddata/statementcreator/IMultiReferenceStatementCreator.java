/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceStatementCreator {
  String createStatementToDeleteMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  String createStatementToDeleteMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);

  String createStatementToInsertMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId,
    String referencedEntityTableId);
}
