package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;

public final class ColumnTool extends DatabaseObjectTool implements IColumnTool {

  private static final IParameterizedFieldTypeTool PARAMETERIZED_FIELD_TYPE_TOOL = //
  new ParameterizedFieldTypeTool();

  @Override
  public void assertBelongsToTable(final IColumn column) {
    if (!column.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(column, ITable.class);
    }
  }

  @Override
  public void assertDoesNotBelongToTable(final IColumn column) {
    if (column.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(column, column.getParentTable());
    }
  }

  @Override
  public void assertIsABackReferenceColumn(final IColumn column) {
    if (!isABackReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not a back reference column");
    }
  }

  @Override
  public void assertIsAReferenceColumn(final IColumn column) {
    if (!isAReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not any reference column");
    }
  }

  @Override
  public boolean belongsToDatabase(final IColumn column) {
    return (column.belongsToTable() && column.getParentTable().belongsToDatabase());
  }

  @Override
  public BaseContentType getBaseFieldType(IColumn column) {
    return getFieldType(column).getBaseType();
  }

  @Override
  public ContentType getFieldType(final IColumn column) {
    return column.getParameterizedFieldType().getFieldType();
  }

  @Override
  public IDatabase getParentDatabase(final IColumn column) {
    return column.getParentTable().getParentDatabase();
  }

  @Override
  public boolean isABackReferenceColumn(final IColumn column) {
    return PARAMETERIZED_FIELD_TYPE_TOOL.isABaseBackReferenceType(column.getParameterizedFieldType());
  }

  @Override
  public boolean isAReferenceColumn(final IColumn column) {
    return PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(column.getParameterizedFieldType());
  }

  @Override
  public boolean isAValueColumn(final IColumn column) {
    return PARAMETERIZED_FIELD_TYPE_TOOL.isABaseValueType(column.getParameterizedFieldType());
  }

  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {

    if (!isABackReferenceColumn(column)) {
      return false;
    }

    final var parameterizedFieldType = column.getParameterizedFieldType();

    final var backReferencedColumn = parameterizedFieldType.asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedColumnParameterizedFieldType = backReferencedColumn.getParameterizedFieldType();

    if (!PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(backReferencedColumnParameterizedFieldType)) {
      return false;
    }

    return referencesGivenTable(backReferencedColumn, column.getParentTable());
  }

  @Override
  public boolean referencesBackGivenColumn(
    final IColumn column,
    final IColumn probableBackReferencedColumn) {
    return column.getParameterizedFieldType().referencesBackColumn(probableBackReferencedColumn);
  }

  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.getParameterizedFieldType().referencesTable(table);
  }
}
