//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.SqlSyntaxCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeSqlRecordMapper {

  // method
  public ParameterizedPropertyTypeSqlRecord createParameterizedPropertyTypeRecordFrom(
      final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    return switch (parameterizedPropertyType.getPropertyType().getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeRecord((IBaseParameterizedValueTypeDto) parameterizedPropertyType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeRecord((IBaseParameterizedReferenceTypeDto) parameterizedPropertyType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceRecord(
            (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedPropertyType);
    };
  }

  // method
  private ParameterizedPropertyTypeSqlRecord createBaseParameterizedBackReferenceRecord(
      final IBaseParameterizedBackReferenceTypeDto baseParameterizedBackReferenceType) {
    return new ParameterizedPropertyTypeSqlRecord(
        "'" + baseParameterizedBackReferenceType.getPropertyType().toString() + "'",
        "'" + baseParameterizedBackReferenceType.getDataType().name() + "'",
        SqlSyntaxCatalogue.NULL,
        "'" + baseParameterizedBackReferenceType.getBackReferencedColumnId() + "'");
  }

  // method
  private ParameterizedPropertyTypeSqlRecord createBaseParameterizedReferenceTypeRecord(
      final IBaseParameterizedReferenceTypeDto baseParameterizedReferenceType) {
    return new ParameterizedPropertyTypeSqlRecord(
        "'" + baseParameterizedReferenceType.getPropertyType().toString() + "'",
        "'" + baseParameterizedReferenceType.getDataType().name() + "'",
        "'" + baseParameterizedReferenceType.getReferencedTableId() + "'",
        SqlSyntaxCatalogue.NULL);
  }

  // method
  private ParameterizedPropertyTypeSqlRecord createBaseParameterizedValueTypeRecord(
      final IBaseParameterizedValueTypeDto baseParameterizedPropertyType) {
    return new ParameterizedPropertyTypeSqlRecord(
        "'" + baseParameterizedPropertyType.getPropertyType().toString() + "'",
        "'" + baseParameterizedPropertyType.getDataType().name() + "'",
        SqlSyntaxCatalogue.NULL,
        SqlSyntaxCatalogue.NULL);
  }
}
