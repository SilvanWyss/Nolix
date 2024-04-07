//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public final class ParameterizedMultiValueType<V> extends BaseParameterizedValueType<V> {

  //constructor
  private ParameterizedMultiValueType(final Class<V> valueType) {
    super(valueType);
  }

  //static method
  public static <V2> ParameterizedMultiValueType<V2> forValueType(final Class<V2> valueType) {
    return new ParameterizedMultiValueType<>(valueType);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_VALUE;
  }
}
