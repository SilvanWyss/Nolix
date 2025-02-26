package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.OptionalReferenceModelView;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;

public final class OptionalReferenceModelMapper
implements IContentModelDtoToContentModelMapper<OptionalReferenceModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final OptionalReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableId = contentModelDto.referencedTableId();
    final var table = referencableTables.getStoredFirst(t -> t.hasId(tableId));

    return OptionalReferenceModelView.forReferencedTable(table);
  }
}
