//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programstructure.data.BinaryObject;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;

//class
public final class ValueCreator {

  //method
  public Object createValueOfDataTypeFromString(final DataType dataType, final String string) {
    return switch (dataType) {
      case INTEGER_1BYTE ->
        Byte.valueOf(string);
      case INTEGER_2BYTE ->
        Short.valueOf(string);
      case INTEGER_4BYTE ->
        Integer.valueOf(string);
      case INTEGER_8BYTE ->
        Long.valueOf(string);
      case FLOATING_POINT_NUMBER_4BYTE ->
        Float.valueOf(string);
      case FLOATING_POINT_NUMBER_8BYTE ->
        Double.valueOf(string);
      case BOOLEAN ->
        Boolean.valueOf(string);
      case STRING ->
        string;
      case BINARY_OBJECT ->
        BinaryObject.fromString(string);
      default ->
        throw InvalidArgumentException.forArgument(dataType);
    };
  }
}
