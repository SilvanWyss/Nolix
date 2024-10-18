package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public final class ParameterizedFieldTypeSqlRecordMapper {

  private static final String NULL = "NULL";

  public ParameterizedFieldTypeSqlRecord createParameterizedFieldTypeRecordFrom(
    final IParameterizedFieldTypeDto parameterizedFieldType) {
    return switch (parameterizedFieldType.getFieldType().getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeRecord((IBaseParameterizedValueTypeDto) parameterizedFieldType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeRecord((IBaseParameterizedReferenceTypeDto) parameterizedFieldType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceRecord(
          (IBaseParameterizedBackReferenceTypeDto) parameterizedFieldType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedFieldType);
    };
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedBackReferenceRecord(
    final IBaseParameterizedBackReferenceTypeDto baseParameterizedBackReferenceType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedBackReferenceType.getFieldType().toString() + "'",
      "'" + baseParameterizedBackReferenceType.getDataType().name() + "'",
      NULL,
      "'" + baseParameterizedBackReferenceType.getBackReferencedColumnId() + "'");
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedReferenceTypeRecord(
    final IBaseParameterizedReferenceTypeDto baseParameterizedReferenceType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedReferenceType.getFieldType().toString() + "'",
      "'" + baseParameterizedReferenceType.getDataType().name() + "'",
      "'" + baseParameterizedReferenceType.getReferencedTableId() + "'",
      NULL);
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedValueTypeRecord(
    final IBaseParameterizedValueTypeDto baseParameterizedFieldType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedFieldType.getFieldType().toString() + "'",
      "'" + baseParameterizedFieldType.getDataType().name() + "'",
      NULL,
      NULL);
  }
}
