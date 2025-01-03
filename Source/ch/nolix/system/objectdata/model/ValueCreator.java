package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.blob.Blob;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;

public final class ValueCreator {

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
        Blob.fromString(string);
      default ->
        throw InvalidArgumentException.forArgument(dataType);
    };
  }
}
