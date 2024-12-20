package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public abstract class BaseParameterizedBackReferenceType extends ParameterizedFieldType
implements IBaseParameterizedBackReferenceType {

  private final IColumn backReferencedColumn;

  protected BaseParameterizedBackReferenceType(final IColumn backReferencedColumn) {

    super(DataType.STRING);

    assertIsAnyReferenceColumn(backReferencedColumn);

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType() {
    return this;
  }

  @Override
  public final IBaseParameterizedReferenceType asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public IColumn getBackReferencedColumn() {
    return backReferencedColumn;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return (getBackReferencedColumn() == column);
  }

  @Override
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedBackReferenceTypeDto(
      getFieldType(),
      getDataType(),
      getBackReferencedColumn().getId());
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
    return backReferencedColumn.getParameterizedFieldType().getFieldType()
      .getBaseType() == BaseContentType.BASE_REFERENCE;
  }
}
