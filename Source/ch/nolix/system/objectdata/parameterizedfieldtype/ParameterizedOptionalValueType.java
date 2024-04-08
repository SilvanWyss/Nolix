//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

//own imports
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public final class ParameterizedOptionalValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedOptionalValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedOptionalValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedOptionalValueType<>(valueType);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_VALUE;
  }
}
