package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public abstract class AbstractBackReferenceModel implements IAbstractBackReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private final IColumn backReferencedColumn;

  protected AbstractBackReferenceModel(final IColumn backReferencedColumn) {

    assertIsAnyReferenceColumn(backReferencedColumn);

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IAbstractBackReferenceModel asBaseParameterizedBackReferenceType() {
    return this;
  }

  @Override
  public final IAbstractReferenceModel asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
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
    if (!isAnyReferenceColumn(backReferencedColumn)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "back referenced column",
        backReferencedColumn,
        "is not any refence column");
    }
  }

  private boolean isAnyReferenceColumn(IColumn backReferencedColumn) {
    return backReferencedColumn.getContentModel().getContentType()
      .getBaseType() == BaseContentType.BASE_REFERENCE;
  }
}
