/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.schemareader;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmidschema.modelmapper.TableDtoMapper;
import ch.nolix.system.sqlmidschema.querycreator.QueryCreator;
import ch.nolix.system.time.main.Time;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class SchemaReader implements ISchemaReader {
  private static final QueryCreator QUERY_CREATOR = new QueryCreator();

  private static final TableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final ch.nolix.systemapi.sqlschema.adapter.ISchemaReader sqlSchemaReader;

  private SchemaReader(final String databaseName, final ISqlConnection sqlConnection) {
    this.sqlSchemaReader = //
    ch.nolix.system.sqlschema.adapter.SchemaReader.forDatabaseNameAndSqlConnection(
      databaseName,
      sqlConnection);

    this.sqlConnection = sqlConnection;
  }

  public static SchemaReader forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SchemaReader(databaseName, sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return sqlSchemaReader.columnIsEmpty(tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTableCount() {
    final var query = QUERY_CREATOR.createQueryToGetTableCount();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var value = sqlRecord.getStoredSingle();

    return Integer.valueOf(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time getSchemaTimestamp() {
    final var query = QUERY_CREATOR.createQueryToLoadSchemaTimestamp();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var value = sqlRecord.getStoredSingle();

    return Time.fromString(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto loadTable(final String tableName) {
    final var query = QUERY_CREATOR.createQueryToLoadJoinedColumns(tableName);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapJoinedColumnSqlRecordsToTableDto(sqlRecords);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<TableDto> loadTables() {
    final var query = QUERY_CREATOR.createQueryToLoadJoinedColumns();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapJoinedColumnSqlRecordsToTableDtos(sqlRecords);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing.
  }
}
