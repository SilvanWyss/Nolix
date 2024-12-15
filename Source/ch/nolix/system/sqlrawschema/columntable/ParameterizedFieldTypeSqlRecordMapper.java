package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public final class ParameterizedFieldTypeSqlRecordMapper {

  private static final String NULL = "NULL";

  public ParameterizedFieldTypeSqlRecord createParameterizedFieldTypeRecordFrom(
    final IContentModelDto parameterizedFieldType) {
    return switch (parameterizedFieldType.getFieldType().getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeRecord((IAbstractValueModelDto) parameterizedFieldType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeRecord((IAbstractReferenceModelDto) parameterizedFieldType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceRecord(
          (IAbstractBackReferenceModelDto) parameterizedFieldType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedFieldType);
    };
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedBackReferenceRecord(
    final IAbstractBackReferenceModelDto baseParameterizedBackReferenceType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedBackReferenceType.getFieldType().toString() + "'",
      "'" + baseParameterizedBackReferenceType.getDataType().name() + "'",
      NULL,
      "'" + baseParameterizedBackReferenceType.getBackReferencedColumnId() + "'");
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedReferenceTypeRecord(
    final IAbstractReferenceModelDto baseParameterizedReferenceType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedReferenceType.getFieldType().toString() + "'",
      "'" + baseParameterizedReferenceType.getDataType().name() + "'",
      "'" + baseParameterizedReferenceType.getReferencedTableId() + "'",
      NULL);
  }

  private ParameterizedFieldTypeSqlRecord createBaseParameterizedValueTypeRecord(
    final IAbstractValueModelDto baseParameterizedFieldType) {
    return new ParameterizedFieldTypeSqlRecord(
      "'" + baseParameterizedFieldType.getFieldType().toString() + "'",
      "'" + baseParameterizedFieldType.getDataType().name() + "'",
      NULL,
      NULL);
  }
}
