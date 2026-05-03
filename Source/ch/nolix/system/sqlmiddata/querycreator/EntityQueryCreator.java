/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.sqlmiddata.querycreator.IEntityQueryCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.tablestructure.MetaDataTable;

/**
 * @author Silvan Wyss
 */
public final class EntityQueryCreator implements IEntityQueryCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountEntities(final String tableName) {
    return //
    "SELECT COUNT(Id) FROM "
    + tableName
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return //
    "SELECT COUNT(Id) FROM "
    + tableName
    + " WHERE Id = '"
    + id
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountEntitiesWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return //
    "SELECT COUNT("
    + columnName
    + ") FROM "
    + tableName
    + " WHERE "
    + columnName
    + " = '"
    + value
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT("
    + columnName
    + ") FROM "
    + tableName
    + " WHERE "
    + columnName
    + " = '"
    + value
    + "' AND Id NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadEntitiesByTable(final TableInfoDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnViews().getViewOf(ColumnInfoDto::name).toStringWithSeparator(", ")
    + " FROM "
    + tableView.name()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadEntityByTableAndId(String id, TableInfoDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnViews().getViewOf(ColumnInfoDto::name).toStringWithSeparator(", ")
    + " FROM "
    + tableView.name()
    + " WHERE Id = '"
    + id
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyColumn.VALUE
    + " FROM "
    + MetaDataTable.DATABASE_PROPERTY
    + " WHERE "
    + DatabasePropertyColumn.KEY
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getName()
    + "';";
  }
}
