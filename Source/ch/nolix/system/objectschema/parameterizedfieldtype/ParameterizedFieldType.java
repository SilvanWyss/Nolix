package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;

public abstract class ParameterizedFieldType implements IParameterizedFieldType {

  private final DataType dataType;

  protected ParameterizedFieldType(final DataType dataTye) {

    GlobalValidator.assertThat(dataTye).thatIsNamed(DataType.class).isNotNull();

    this.dataType = dataTye;
  }

  @Override
  public final DataType getDataType() {
    return dataType;
  }
}
