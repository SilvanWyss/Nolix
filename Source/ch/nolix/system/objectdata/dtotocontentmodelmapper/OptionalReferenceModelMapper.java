package ch.nolix.system.objectdata.dtotocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.OptionalReferenceModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;

public final class OptionalReferenceModelMapper
implements IContentModelDtoToContentModelMapper<OptionalReferenceModelDto> {

  @Override
  public IContentModel mapContentModelDtoToContentModel(
    final OptionalReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableIds = contentModelDto.referencedTableIds();
    final var tables = referencableTables.getStoredSelected(t -> tableIds.containsEqualing(t.getId()));

    //TODO: Handle multiple referenced tables
    return OptionalReferenceModel.forReferencedTable(tables.getStoredFirst());
  }
}
