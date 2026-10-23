/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityQueryCreator {
  /**
   * @param tableName
   * @return a query to count the entities of the table with the given tableName
   */
  String createQueryToCountEntities(String tableName);

  /**
   * @param tableName
   * @param id
   * @return a query to count the entities with the given id of the table with the
   *         given tableName
   */
  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  /**
   * @param tableName
   * @param columnName
   * @param value
   * @return a query to count the entities of the table with the given tableName
   *         that have the given value at the column with the given columnName
   */
  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  /**
   * @param tableName
   * @param columnName
   * @param value
   * @param entitiesToIgnoreIds
   * @return a query to count the entities, that do not have one of the given
   *         entitiesToIgnoreIds, of the table with the given tableName that have
   *         the given value at the column with the given columnName
   */
  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    ExtendedIterable<String> entitiesToIgnoreIds);

  /**
   * @param tableView
   * @return a query to load the entities of the given tableView
   */
  String createQueryToLoadEntitiesByTable(TableInfoDto tableView);

  /**
   * @param id
   * @param tableView
   * @return a query to load the entity with the given id of the given tableView
   */
  String createQueryToLoadEntityByTableAndId(String id, TableInfoDto tableView);

  /**
   * @return a query to load the schema timestamp
   */
  String createQueryToLoadSchemaTimestamp();
}
