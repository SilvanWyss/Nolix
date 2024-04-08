//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

//own imports
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public final class ParameterizedValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedValueType<>(valueType);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.VALUE;
  }
}
