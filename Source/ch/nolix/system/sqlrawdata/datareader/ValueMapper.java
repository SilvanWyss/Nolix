package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnInfo;

public final class ValueMapper {

  public Object createValueFromString(final String string, final IColumnInfo contentColumnDefinition) {
    return createValueFromString(string, contentColumnDefinition.getColumnDataType());
  }

  private Object createValueFromString(final String string, final DataType dataType) {
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
      default ->
        throw InvalidArgumentException.forArgument(dataType);
    };
  }
}
