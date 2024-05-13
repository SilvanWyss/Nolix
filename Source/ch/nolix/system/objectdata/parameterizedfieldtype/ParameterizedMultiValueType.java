//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
  public ContentType getFieldType() {
    return ContentType.MULTI_VALUE;
  }
}
