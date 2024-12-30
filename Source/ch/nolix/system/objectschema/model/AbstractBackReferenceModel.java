package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IColumnExaminer;

public abstract class AbstractBackReferenceModel implements IAbstractBackReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private static final IColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  private final IColumn backReferencedColumn;

  protected AbstractBackReferenceModel(final IColumn backReferencedColumn) {

    assertIsAnyReferenceColumn(backReferencedColumn);

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IColumn getBackReferencedColumn() {
    return backReferencedColumn;
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return (getBackReferencedColumn() == column);
  }

  private void assertIsAnyReferenceColumn(IColumn backReferencedColumn) {
    if (!COLUMN_EXAMINER.isAbstractReferenceColumn(backReferencedColumn)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "back referenced column",
        backReferencedColumn,
        "is not any refence column");
    }
  }
}
