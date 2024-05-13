//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

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
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_VALUE;
  }
}
