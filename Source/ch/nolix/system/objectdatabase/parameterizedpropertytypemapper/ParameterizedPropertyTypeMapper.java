//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedBackReferenceType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedMultiReferenceType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedOptionalReferenceType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedOptionalValueType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedReferenceType;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeMapper {

  //method
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDtoUsingGivenReferencableTables(
    final IParameterizedPropertyTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    switch (parameterizedPropertyTypeDto.getPropertyType()) {
      case VALUE:

        final var valueType = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

        return ParameterizedValueType.forValueType(valueType);
      case OPTIONAL_VALUE:

        final var valueType2 = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

        return ParameterizedOptionalValueType.forValueType(valueType2);
      case MULTI_VALUE:

        final var valueType3 = parameterizedPropertyTypeDto.getDataType().getDataTypeClass();

        return ParameterizedMultiValueType.forValueType(valueType3);
      case REFERENCE:

        final var baseParameterizedReferenceTypeDto = (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto;

        final var referencedTable = referencableTables
          .getStoredFirst(t -> t.hasId(baseParameterizedReferenceTypeDto.getReferencedTableId()));

        return ParameterizedReferenceType.forReferencedTable(referencedTable);
      case OPTIONAL_REFERENCE:

        final var baseParameterizedReferenceTypeDto2 = //
        (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto;

        final var referencedTable2 = referencableTables
          .getStoredFirst(t -> t.hasId(baseParameterizedReferenceTypeDto2.getReferencedTableId()));

        return ParameterizedOptionalReferenceType.forReferencedTable(referencedTable2);
      case MULTI_REFERENCE:

        final var baseParameterizedReferenceTypeDto3 = //
        (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto;

        final var referencedTable3 = referencableTables
          .getStoredFirst(t -> t.hasId(baseParameterizedReferenceTypeDto3.getReferencedTableId()));

        return new ParameterizedMultiReferenceType<>(referencedTable3);
      case BACK_REFERENCE:

        final var baseParameterizedBackRefenceTypeDto = //
        (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto;

        final var backReferencedColumn = referencableTables
          .toFromGroups(ITable::getStoredColumns)
          .getStoredFirst(c -> c.hasId(baseParameterizedBackRefenceTypeDto.getBackReferencedColumnId()));

        return new ParameterizedBackReferenceType<>(backReferencedColumn);
      case OPTIONAL_BACK_REFERENCE:

        final var baseParameterizedBackRefenceTypeDto2 = //
        (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto;

        final var backReferencedColumn2 = referencableTables
          .toFromGroups(ITable::getStoredColumns)
          .getStoredFirst(c -> c.hasId(baseParameterizedBackRefenceTypeDto2.getBackReferencedColumnId()));

        return new ParameterizedBackReferenceType<>(backReferencedColumn2);
      case MULTI_BACK_REFERENCE:

        final var baseParameterizedBackRefenceTypeDto3 = //
        (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto;

        final var backReferencedColumn3 = referencableTables
          .toFromGroups(ITable::getStoredColumns)
          .getStoredFirst(c -> c.hasId(baseParameterizedBackRefenceTypeDto3.getBackReferencedColumnId()));

        return new ParameterizedBackReferenceType<>(backReferencedColumn3);
      default:
        throw InvalidArgumentException.forArgument(parameterizedPropertyTypeDto);
    }
  }
}
