package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.dtomapper.ColumnDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.dtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.flatdto.FlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class SchemaReader implements ISchemaReader {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final IQueryCreator queryCreator;

  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator queryCreator) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
    RESOURCE_VALIDATOR.assertIsOpen(sqlConnection);
    GlobalValidator.assertThat(queryCreator).thatIsNamed(IQueryCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    createCloseDependencyTo(sqlConnection);

    this.queryCreator = queryCreator;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public static SchemaReader forDatabaseNameAndSqlConnectionAndQueryCreator(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator queryCreator) {
    return new SchemaReader(databaseName, sqlConnection, queryCreator);
  }

  @Override
  public boolean columnsIsEmpty(final String tableName, final String columnName) {

    final var query = queryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.isEmpty();
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public IContainer<ColumnDto> loadColumns(final String tableName) {

    final var query = queryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.to(COLUMN_DTO_MAPPER::mapSqlRecordToColumnDto);
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {

    final var query = queryCreator.createQueryToLoadNameOfTables();
    final var records = sqlConnection.getRecordsFromQuery(query);

    //TODO: Create FlatTableDtoMapper
    return records.to(r -> new FlatTableDto(r.getStoredOne()));
  }

  @Override
  public IContainer<TableDto> loadTables() {
    return loadFlatTables().to(t -> new TableDto(t.name(), loadColumns(t.name())));
  }

  @Override
  public void noteClose() {
    //Does nothing
  }

  @Override
  public boolean tableExists(String tableName) {

    final var query = queryCreator.createQueryToLoadTable(tableName);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.containsAny();
  }
}
