package ch.nolix.core.datamodel.fliedvalue;

import ch.nolix.core.datamodel.blob.Blob;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.datamodel.fieldvalue.IValueMapper;

/**
 * @author Silvan Wyss
 * @version 2025-03-02
 */
public final class ValueMapper implements IValueMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object mapStringToValue(final String string, final DataType dataType) {
    return //
    switch (dataType) {
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
