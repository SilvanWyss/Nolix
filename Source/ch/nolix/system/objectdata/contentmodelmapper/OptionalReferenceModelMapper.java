package ch.nolix.system.objectdata.contentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.OptionalReferenceModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;

public final class OptionalReferenceModelMapper
implements IContentModelMapper<OptionalReferenceModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    final OptionalReferenceModelDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableIds = parameterizedFieldTypeDto.referencedTableIds();
    final var tables = referencableTables.getStoredSelected(t -> tableIds.containsEqualing(t.getId()));

    //TODO: Handle multiple referenced tables
    return OptionalReferenceModel.forReferencedTable(tables.getStoredFirst());
  }
}
