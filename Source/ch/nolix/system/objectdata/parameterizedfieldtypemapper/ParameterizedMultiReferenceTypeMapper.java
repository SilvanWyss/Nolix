package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.MultiReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;

public final class ParameterizedMultiReferenceTypeMapper
implements IParameterizedFieldTypeMapper<MultiReferenceModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    MultiReferenceModelDto parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableIds = parameterizedFieldTypeDto.referencedTableIds();
    final var tables = referencableTables.getStoredSelected(t -> tableIds.containsEqualing(t.getId()));

    //TODO: Handle multiple referenced tables
    return MultiReferenceModel.forReferencedTable(tables.getStoredFirst());
  }
}
