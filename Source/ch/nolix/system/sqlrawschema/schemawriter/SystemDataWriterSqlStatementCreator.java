//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.system.sqlrawschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawschema.columntable.ParameterizedFieldTypeSqlRecordMapper;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlrawschema.tabletable.TableTableRecordMapper;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlrawschemaapi.schemawriterapi.ISystemDataWriterSqlStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class SystemDataWriterSqlStatementCreator implements ISystemDataWriterSqlStatementCreator {

  //constant
  private static final ParameterizedFieldTypeSqlRecordMapper PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER = //
  new ParameterizedFieldTypeSqlRecordMapper();

  //constant
  private static final TableTableRecordMapper TABLE_TABLE_RECORD_MAPPER = new TableTableRecordMapper();

  //method
  @Override
  public String createStatementToAddColumn(final String parentTableName, final IColumnDto column) {

    final var parameterizedFieldTypeRecord = PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER
      .createParameterizedFieldTypeRecordFrom(
        column.getParameterizedFieldType());

    return "INSERT INTO "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " ("
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + ") SELECT '"
    + column.getId()
    + "', "
    + TableTableColumn.ID.getQualifiedName()
    + ", '"
    + column.getName()
    + "', "
    + parameterizedFieldTypeRecord.getFieldTypeValue()
    + ", "
    + parameterizedFieldTypeRecord.getDataTypeValue()
    + ", "
    + parameterizedFieldTypeRecord.getReferencedTableIdValue()
    + ", "
    + parameterizedFieldTypeRecord.getBackReferencedColumnIdValue()
    + " FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + " WHERE "
    + TableTableColumn.NAME.getQualifiedName()
    + " = '"
    + parentTableName
    + "'";
  }

  //method
  @Override
  public LinkedList<String> createStatementsToAddTable(final ITableDto table) {

    final var statements = new LinkedList<String>();

    statements.addAtEnd(createStatementToAddTableIgnoringColumns(table));

    for (final var c : table.getColumns()) {
      statements.addAtEnd(createStatementToAddColumn(table.getName(), c));
    }

    return statements;
  }

  //method
  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return "DELETE FROM "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " WHERE "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = "
    + tableName
    + " AND "
    + ColumnTableColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  //method
  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return "DELETE FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + " WHERE "
    + TableTableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  //method
  @Override
  public String createStatementToSetColumnName(final String tableName, final String columnName,
    final String newColumnName) {
    return "UPDATE "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " SET "
    + ColumnTableColumn.NAME
    + " = '"
    + newColumnName
    + "' WHERE "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "' AND "
    + ColumnTableColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  //method
  @Override
  public String createStatementToSetColumnParameterizedFieldType(
    final String columnID,
    final IParameterizedFieldTypeDto parameterizedFieldType) {

    final var parameterizedFieldTypeRecord = PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER
      .createParameterizedFieldTypeRecordFrom(parameterizedFieldType);

    return "UPDATE "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " SET "
    + ColumnTableColumn.DATA_TYPE
    + " = "
    + parameterizedFieldTypeRecord.getDataTypeValue()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID
    + " = "
    + parameterizedFieldTypeRecord.getReferencedTableIdValue()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID
    + " = "
    + parameterizedFieldTypeRecord.getBackReferencedColumnIdValue()
    + "WHERE"
    + ColumnTableColumn.ID
    + " = '"
    + columnID
    + "'";
  }

  //method
  @Override
  public String createStatementToSetSchemaTimestamp(final ITime schemaTimestamp) {
    return "UPDATE "
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + " SET "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "' WHERE "
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
  }

  //method
  @Override
  public String createStatementToSetTableName(final String tableName, final String newTableName) {
    return "UPDATE "
    + SchemaTableType.TABLE.getQualifiedName()
    + " SET "
    + TableTableColumn.NAME.getName()
    + " = '"
    + newTableName
    + "' WHERE "
    + TableTableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";
  }

  //method
  private String createStatementToAddTableIgnoringColumns(final ITableDto table) {

    final var tableSystemTableRecord = TABLE_TABLE_RECORD_MAPPER.createTableSystemTableRecordFrom(table);

    return "INSERT INTO "
    + SchemaTableType.TABLE.getQualifiedName()
    + " ("
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + ") VALUES ("
    + tableSystemTableRecord.getIdValue()
    + ", "
    + tableSystemTableRecord.getNameValue()
    + ")";
  }
}
