package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.MultiReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;

public final class MultiReferenceModelMapper
implements IContentModelDtoToContentModelMapper<MultiReferenceModelDto> {

  @Override
  public IContentModelView mapContentModelDtoToContentModel(
    MultiReferenceModelDto contentModelDto,
    IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableIds = contentModelDto.referencedTableIds();
    final var tables = referencableTables.getStoredSelected(t -> tableIds.containsEqualing(t.getId()));

    //TODO: Handle multiple referenced tables
    return MultiReferenceModel.forReferencedTable(tables.getStoredFirst());
  }
}
