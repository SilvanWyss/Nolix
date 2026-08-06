/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.adapter;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlschema.modelmapper.TableDtoMapper;
import ch.nolix.system.sqlschema.querycreator.QueryCreator;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaReader;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class SchemaReader implements ISchemaReader {
  private static final QueryCreator QUERY_CREATOR = new QueryCreator();

  private static final TableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private SchemaReader(final String databaseName, final ISqlConnection sqlConnection) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE_NAME).isNotBlank();
    ResourceValidator.assertIsOpen(sqlConnection);

    this.sqlConnection = sqlConnection;
    createCloseDependencyTo(sqlConnection);

    sqlConnection.executeStatement("USE " + databaseName);
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
    final var query = QUERY_CREATOR.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.isEmpty();
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

    return Integer.valueOf(sqlRecord.getStoredFirstNonNull());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto loadTable(String tableName) {
    final var query = QUERY_CREATOR.createQueryToLoadNameAndDataTypeOfColumns(tableName);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapSqlRecordsWithNameAndDataTypeToTableDto(tableName, sqlRecords);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<TableDto> loadTables() {
    final var query = QUERY_CREATOR.createQueryToLoadTableNameAndNameAndDataTypeOfColumns();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(sqlRecords);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableExist() {
    return (getTableCount() > 0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableExists(String tableName) {
    final var query = QUERY_CREATOR.createQueryToGetTableCount(tableName);
    final var records = sqlConnection.getSingleRecordFromQuery(query);

    return (Integer.valueOf(records.getStoredFirstNonNull()) > 0);
  }
}
