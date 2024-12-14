package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public abstract class BaseParameterizedReferenceType extends ParameterizedFieldType
implements IBaseReferenceModel {

  private final ITable referencedTable;

  protected BaseParameterizedReferenceType(final ITable referencedTable) {

    super(DataType.STRING);

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IBaseBackReferenceModel asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IBaseReferenceModel asBaseParameterizedReferenceType() {
    return this;
  }

  @Override
  public final IBaseValueModel<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public ITable getReferencedTable() {
    return referencedTable;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return (getReferencedTable() == table);
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }

  @Override
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedReferenceTypeDto(
      getContentType(),
      getDataType(),
      getReferencedTable().getId());
  }
}
