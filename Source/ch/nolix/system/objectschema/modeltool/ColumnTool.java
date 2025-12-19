package ch.nolix.system.objectschema.modeltool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

/**
 * @author Silvan Wyss
 */
public final class ColumnTool extends DatabaseObjectExaminer implements IColumnTool {

  @Override
  public void assertBelongsToTable(final IColumn column) {
    if (!column.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(column, ITable.class);
    }
  }

  @Override
  public void assertDoesNotBelongToTable(final IColumn column) {
    if (column.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(column, column.getStoredParentTable());
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
    return (column.belongsToTable() && column.getStoredParentTable().belongsToDatabase());
  }

  @Override
  public BaseFieldType getBaseFieldType(IColumn column) {
    return column.getFieldType().getBaseType();
  }

  @Override
  public IDatabase getParentDatabase(final IColumn column) {
    return column.getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public boolean isABackReferenceColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE;
  }

  @Override
  public boolean isAReferenceColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }

  @Override
  public boolean isAValueColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_VALUE_FIELD;
  }

  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {
    final var fieldType = column.getFieldType();
    final var baseType = fieldType.getBaseType();

    if (baseType == BaseFieldType.BASE_BACK_REFERENCE) {
      final var table = column.getStoredParentTable();
      final var backReferenceableColumns = column.getStoredBackReferenceableColumns();

      for (final var c : backReferenceableColumns) {
        if (!isAReferenceColumn(c) || !referencesGivenTable(c, table)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  @Override
  public boolean referencesBackGivenColumn(final IColumn column, final IColumn probableBackReferencedColumn) {
    return column.referencesBackColumn(probableBackReferencedColumn);
  }

  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.referencesTable(table);
  }
}
