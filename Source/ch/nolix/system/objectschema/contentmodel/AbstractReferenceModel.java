package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public abstract class AbstractReferenceModel implements IAbstractReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private final ITable referencedTable;

  protected AbstractReferenceModel(final ITable referencedTable) {

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IAbstractBackReferenceModel asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel asBaseParameterizedReferenceType() {
    return this;
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final ITable getReferencedTable() {
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
