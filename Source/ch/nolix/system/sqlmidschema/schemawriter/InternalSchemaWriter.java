package ch.nolix.system.sqlmidschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaColumnDtoMapper;
import ch.nolix.system.sqlmidschema.sqlschemamodelmapper.SqlSchemaTableDtoMapper;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaTableDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;

public final class InternalSchemaWriter implements ChangeRequestable {

  private static final ISqlSchemaTableDtoMapper SQL_SCHEMA_TABLE_DTO_MAPPER = new SqlSchemaTableDtoMapper();

  private static final ISqlSchemaColumnDtoMapper SQL_SCHEMA_COLUMN_DTO_MAPPER = new SqlSchemaColumnDtoMapper();

  private final ISchemaWriter sqlSchemaWriter;

  public InternalSchemaWriter(final ISchemaWriter sqlSchemaWriter) {

    Validator.assertThat(sqlSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();

    this.sqlSchemaWriter = sqlSchemaWriter;
  }

  public void addColumn(final String tableName, final ColumnDto column) {
    sqlSchemaWriter.addColumn(tableName, SQL_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToSqlSchemaColumnDto(column));
  }

  public void addTable(final TableDto table) {
    sqlSchemaWriter.addTable(SQL_SCHEMA_TABLE_DTO_MAPPER.mapTableDtoSqlSchemaTableDto(table));
  }

  public void deleteColumn(final String tableName, final String columnName) {
    sqlSchemaWriter.deleteColumn(tableName, columnName);
  }

  public void deleteTable(final String tableName) {
    sqlSchemaWriter.deleteTable(tableName);
  }

  public IContainer<String> getSqlStatements() {
    return sqlSchemaWriter.getSqlStatements();
  }

  @Override
  public boolean hasChanges() {
    return sqlSchemaWriter.hasChanges();
  }

  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    sqlSchemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  public void renameTable(final String tableName, final String newTableName) {
    sqlSchemaWriter.renameTable(tableName, newTableName);
  }

  public void reset() {
    sqlSchemaWriter.reset();
  }
}
