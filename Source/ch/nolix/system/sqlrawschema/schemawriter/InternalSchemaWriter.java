package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;
import ch.nolix.system.sqlrawschema.sqlschemadtomapper.SqlSchemaDtoMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableNameQualifyingPrefix;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;

final class InternalSchemaWriter implements ChangeRequestable {

  private static final SqlSchemaDtoMapper SCHEMA_DTO_MAPPER = new SqlSchemaDtoMapper();

  private final ISchemaWriter sqlSchemaWriter;

  public InternalSchemaWriter(final ISchemaWriter sqlSchemaWriter) {

    GlobalValidator.assertThat(sqlSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();

    this.sqlSchemaWriter = sqlSchemaWriter;
  }

  public void addColumn(final String tableName, final ColumnDto column) {
    sqlSchemaWriter.addColumn(
      TableNameQualifyingPrefix.E + tableName,
      SCHEMA_DTO_MAPPER.mapColumnDtoToSqlColumnDto(column));
  }

  public void addTable(final TableDto table) {
    sqlSchemaWriter.addTable(SCHEMA_DTO_MAPPER.mapTableDtoToSqlSchemaTableDto(table));
  }

  public void deleteColumn(final String tableName, final String columnName) {
    sqlSchemaWriter.deleteColumn(TableNameQualifyingPrefix.E + tableName, columnName);
  }

  public void deleteTable(final String tableName) {
    sqlSchemaWriter.deleteTable(TableNameQualifyingPrefix.E + tableName);
  }

  public IContainer<String> getSqlStatements() {
    return sqlSchemaWriter.getSqlStatements();
  }

  @Override
  public boolean hasChanges() {
    return sqlSchemaWriter.hasChanges();
  }

  public void reset() {
    sqlSchemaWriter.reset();
  }

  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    sqlSchemaWriter.renameColumn(
      TableNameQualifyingPrefix.E + tableName,
      columnName,
      newColumnName);
  }

  public void setTableName(final String tableName, final String newTableName) {
    sqlSchemaWriter.renameTable(
      TableNameQualifyingPrefix.E + tableName,
      TableNameQualifyingPrefix.E + newTableName);
  }
}
