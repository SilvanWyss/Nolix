//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.parameterizedpropertytype.ParameterizedMultiReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;

//class
public final class ParameterizedMultiReferenceTypeMapper
implements IParameterizedPropertyTypeMapper<IBaseParameterizedReferenceTypeDto> {

  //method
  @Override
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    IBaseParameterizedReferenceTypeDto parameterizedPropertyTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables) {

    final var referencedTableId = parameterizedPropertyTypeDto.getReferencedTableId();
    final var referencedTable = referencableTables.getStoredFirst(t -> t.hasId(referencedTableId));

    return ParameterizedMultiReferenceType.forReferencedTable(referencedTable);
  }
}
