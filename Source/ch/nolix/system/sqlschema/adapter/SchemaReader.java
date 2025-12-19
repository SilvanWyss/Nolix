package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlschema.modelmapper.TableDtoMapper;
import ch.nolix.system.sqlschema.querycreator.QueryCreator;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaReader;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.modelmapper.ITableDtoMapper;
import ch.nolix.systemapi.sqlschema.querycreator.IQueryCreator;

/**
 * @author Silvan Wyss
 */
public final class SchemaReader implements ISchemaReader {
  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private SchemaReader(final String databaseName, final ISqlConnection sqlConnection) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
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

  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    final var query = QUERY_CREATOR.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.isEmpty();
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {
    final var query = QUERY_CREATOR.createQueryToGetTableCount();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return Integer.valueOf(sqlRecord.getStoredFirst());
  }

  @Override
  public TableDto loadTable(String tableName) {
    final var query = QUERY_CREATOR.createQueryToLoadNameAndDataTypeOfColumns(tableName);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapSqlRecordsWithNameAndDataTypeToTableDto(tableName, sqlRecords);
  }

  @Override
  public IContainer<TableDto> loadTables() {
    final var query = QUERY_CREATOR.createQueryToLoadTableNameAndNameAndDataTypeOfColumns();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(sqlRecords);
  }

  @Override
  public void noteClose() {
    //Does nothing
  }

  @Override
  public boolean tableExist() {
    return (getTableCount() > 0);
  }

  @Override
  public boolean tableExists(String tableName) {
    final var query = QUERY_CREATOR.createQueryToGetTableCount(tableName);
    final var records = sqlConnection.getSingleRecordFromQuery(query);

    return (Integer.valueOf(records.getStoredFirst()) > 0);
  }
}
