/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceQueryCreator {
  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @return a query to load the multi back reference entries of the entity with
   *         the given entityId for the multi back reference column with the given
   *         multiBackReferenceColumnId
   */
  String createQueryToLoadMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  /**
   * @param backReferencedTableName
   * @param backReferencedSingleReferenceColumnName
   * @param referencedEntityId
   * @return a query to load the multi back reference entries that reference back
   *         the entity with the given referencedEntityId via the single reference
   *         column with the given backReferencedSingleReferenceColumnName of the
   *         table with the given backReferencedTableName
   */
  String createQueryToLoadMultiBackReferenceEntries(
    String backReferencedTableName,
    String backReferencedSingleReferenceColumnName,
    String referencedEntityId);

  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @return a query to load the ids of the multi back reference entries of the
   *         entity with the given entityId for the multi back reference column
   *         with the given multiBackReferenceColumnId
   */
  String createQueryToLoadMultiBackReferenceEntriesIds(String entityId, String multiBackReferenceColumnId);

  /**
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   * @return a query to load the first multi back reference entry, if there is
   *         one, of the multi back reference column with the given
   *         multiBackReferenceColumnId that references back the entity with the
   *         given backReferencedEntityId
   */
  String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
