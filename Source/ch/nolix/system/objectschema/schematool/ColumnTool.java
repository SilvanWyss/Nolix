//package declaration
package ch.nolix.system.objectschema.schematool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.fieldapi.mainapi.BaseFieldType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;

//class
public final class ColumnTool extends DatabaseObjectTool implements IColumnTool {

  //constant
  private static final IParameterizedFieldTypeTool PARAMETERIZED_PROPERTY_TYPE_TOOL = //
  new ParameterizedFieldTypeTool();

  //method
  @Override
  public void assertBelongsToTable(final IColumn column) {
    if (!column.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(column, ITable.class);
    }
  }

  //method
  @Override
  public void assertDoesNotBelongToTable(final IColumn column) {
    if (column.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(column, column.getParentTable());
    }
  }

  //method
  @Override
  public void assertIsABackReferenceColumn(final IColumn column) {
    if (!isABackReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not a back reference column");
    }
  }

  //method
  @Override
  public void assertIsAReferenceColumn(final IColumn column) {
    if (!isAReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not any reference column");
    }
  }

  //method
  @Override
  public boolean belongsToDatabase(final IColumn column) {
    return (column.belongsToTable() && column.getParentTable().belongsToDatabase());
  }

  //method
  @Override
  public BaseFieldType getBasePropertyType(IColumn column) {
    return getPropertyType(column).getBaseType();
  }

  //method
  @Override
  public IDatabase getParentDatabase(final IColumn column) {
    return column.getParentTable().getParentDatabase();
  }

  //method
  @Override
  public FieldType getPropertyType(final IColumn column) {
    return column.getParameterizedPropertyType().getPropertyType();
  }

  //method
  @Override
  public boolean isABackReferenceColumn(final IColumn column) {
    return PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseBackReferenceType(column.getParameterizedPropertyType());
  }

  //method
  @Override
  public boolean isAReferenceColumn(final IColumn column) {
    return PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseReferenceType(column.getParameterizedPropertyType());
  }

  //method
  @Override
  public boolean isAValueColumn(final IColumn column) {
    return PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseValueType(column.getParameterizedPropertyType());
  }

  //method
  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {

    if (!isABackReferenceColumn(column)) {
      return false;
    }

    final var parameterizedPropertyType = column.getParameterizedPropertyType();

    final var backReferencedColumn = parameterizedPropertyType.asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedColumnParameterizedPropertyType = backReferencedColumn.getParameterizedPropertyType();

    if (!PARAMETERIZED_PROPERTY_TYPE_TOOL.isABaseReferenceType(backReferencedColumnParameterizedPropertyType)) {
      return false;
    }

    return referencesGivenTable(backReferencedColumn, column.getParentTable());
  }

  //method
  @Override
  public boolean referencesBackGivenColumn(
    final IColumn column,
    final IColumn probableBackReferencedColumn) {
    return column.getParameterizedPropertyType().referencesBackColumn(probableBackReferencedColumn);
  }

  //method
  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.getParameterizedPropertyType().referencesTable(table);
  }
}
