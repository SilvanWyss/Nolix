package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.ReferenceModelView;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;

public final class ReferenceModelMapper implements IContentModelDtoToContentModelMapper<ReferenceModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final ReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableId = contentModelDto.referencedTableId();
    final var table = referencableTables.getStoredFirst(t -> t.hasId(tableId));

    return ReferenceModelView.forReferencedTable(table);
  }
}
