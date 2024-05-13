//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public abstract class BaseParameterizedReferenceType extends ParameterizedFieldType
implements IBaseParameterizedReferenceType {

  //attribute
  private final ITable referencedTable;

  //constructor
  protected BaseParameterizedReferenceType(final ITable referencedTable) {

    super(DataType.STRING);

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  //method
  @Override
  public final IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedReferenceType asBaseParameterizedReferenceType() {
    return this;
  }

  //method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  //method
  @Override
  public ITable getReferencedTable() {
    return referencedTable;
  }

  //method
  @Override
  public final boolean referencesTable(final ITable table) {
    return (getReferencedTable() == table);
  }

  //method
  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }

  //method
  @Override
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedReferenceTypeDto(
      getFieldType(),
      getDataType(),
      getReferencedTable().getId());
  }
}
