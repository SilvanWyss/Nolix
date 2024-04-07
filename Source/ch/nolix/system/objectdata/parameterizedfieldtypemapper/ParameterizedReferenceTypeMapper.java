//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;

//class
public final class ParameterizedReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IBaseParameterizedReferenceTypeDto> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedReferenceTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var referencedTableId = parameterizedPropertyTypeDto.getReferencedTableId();
    final var referencedTable = referencableTables.getStoredFirst(t -> t.hasId(referencedTableId));

    return ParameterizedReferenceType.forReferencedTable(referencedTable);
  }
}
