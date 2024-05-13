//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
  public ContentType getFieldType() {
    return ContentType.VALUE;
  }
}
