package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;

final class InternalSchemaWriter implements ChangeRequestable {

  private static final SchemaDtoMapper SCHEMA_DTO_MAPPER = new SchemaDtoMapper();

  private final ISchemaWriter sqlSchemaWriter;

  public InternalSchemaWriter(final ISchemaWriter sqlSchemaWriter) {

    GlobalValidator.assertThat(sqlSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();

    this.sqlSchemaWriter = sqlSchemaWriter;
  }

  public void addColumn(final String tableName, final ColumnDto column) {
    sqlSchemaWriter.addColumn(
      TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName,
      SCHEMA_DTO_MAPPER.createSqlColumnDtoFrom(column));
  }

  public void addTable(final TableDto table) {
    sqlSchemaWriter.addTable(SCHEMA_DTO_MAPPER.createSqlTableDtoFrom(table));
  }

  public void deleteColumn(final String tableName, final String columnName) {
    sqlSchemaWriter.deleteColumn(TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName, columnName);
  }

  public void deleteTable(final String tableName) {
    sqlSchemaWriter.deleteTable(TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName);
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
      TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName,
      columnName,
      newColumnName);
  }

  public void setTableName(final String tableName, final String newTableName) {
    sqlSchemaWriter.renameTable(
      TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName,
      TableType.ENTITY_TABLE.getQualifyingPrefix() + newTableName);
  }
}
