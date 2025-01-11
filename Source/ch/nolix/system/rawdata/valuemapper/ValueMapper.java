package ch.nolix.system.rawdata.valuemapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.rawdataapi.valuemapperapi.IValueMapper;

/**
 * @author Silvan Wyss
 * @version 2021-11-05
 */
public final class ValueMapper implements IValueMapper {

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
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.DATA_TYPE, dataType);
    };
  }
}
