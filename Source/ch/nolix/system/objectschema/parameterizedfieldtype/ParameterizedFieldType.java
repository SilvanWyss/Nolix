//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;

//class
public abstract class ParameterizedFieldType implements IParameterizedFieldType {

  //attribute
  private final DataType dataType;

  //constructor
  protected ParameterizedFieldType(final DataType dataTye) {

    GlobalValidator.assertThat(dataTye).thatIsNamed(DataType.class).isNotNull();

    this.dataType = dataTye;
  }

  //method
  @Override
  public final DataType getDataType() {
    return dataType;
  }
}
