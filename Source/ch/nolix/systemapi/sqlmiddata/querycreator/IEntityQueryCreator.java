/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityQueryCreator {
  String createQueryToCountEntities(String tableName);

  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadEntitiesByTable(TableInfoDto tableView);

  String createQueryToLoadEntityByTableAndId(String id, TableInfoDto tableView);

  String createQueryToLoadSchemaTimestamp();
}
