//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.BaseFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public abstract class BaseParameterizedBackReferenceType extends ParameterizedFieldType
implements IBaseParameterizedBackReferenceType {

  //attribute
  private final IColumn backReferencedColumn;

  //constructor
  protected BaseParameterizedBackReferenceType(final IColumn backReferencedColumn) {

    super(DataType.STRING);

    assertIsAnyReferenceColumn(backReferencedColumn);

    this.backReferencedColumn = backReferencedColumn;
  }

  //method
  @Override
  public final IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType() {
    return this;
  }

  //method
  @Override
  public final IBaseParameterizedReferenceType asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  //method
  @Override
  public IColumn getBackReferencedColumn() {
    return backReferencedColumn;
  }

  //method
  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  //method
  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return (getBackReferencedColumn() == column);
  }

  //method
  @Override
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedBackReferenceTypeDto(
      getPropertyType(),
      getDataType(),
      getBackReferencedColumn().getId());
  }

  //method
  private void assertIsAnyReferenceColumn(IColumn backReferencedColumn) {
    if (!isAnyReferenceColumn(backReferencedColumn)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "back referenced column",
        backReferencedColumn,
        "is not any refence column");
    }
  }

  //method
  private boolean isAnyReferenceColumn(IColumn backReferencedColumn) {
    return backReferencedColumn.getParameterizedPropertyType().getPropertyType()
      .getBaseType() == BaseFieldType.BASE_REFERENCE;
  }
}
