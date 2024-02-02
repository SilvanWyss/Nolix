//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedpropertytype.ParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;

//class
public final class ParameterizedBackReferenceTypeMapper
implements IParameterizedPropertyTypeMapper<IBaseParameterizedBackReferenceTypeDto> {

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedBackReferenceTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = parameterizedPropertyTypeDto.getBackReferencedColumnId();
    final var referencableColumns = referencableTables.toFromGroups(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return ParameterizedBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
