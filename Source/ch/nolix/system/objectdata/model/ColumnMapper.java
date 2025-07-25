package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.modelmapper.ContentModelMapper;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class ColumnMapper {

  private static final ContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  public IColumnView<ITable<IEntity>> mapMidSchemaColumnDtoToColumnView(
    final ColumnDto midSchemaColumnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var id = midSchemaColumnDto.id();
    final var name = midSchemaColumnDto.name();

    final var contentModelView = //
    CONTENT_MODEL_MAPPER.mapContentModelDtoToContentModel(midSchemaColumnDto.contentModel(), referencableTables);

    final var midDataDataAdapterAndSchemaReader = parentTable.getStoredMidDataDataAdapterAndSchemaReader();

    return //
    Column.withIdAndNameAndContentModelViewAndParentTableAndMidDataReader(
      id,
      name,
      contentModelView,
      parentTable,
      midDataDataAdapterAndSchemaReader);
  }
}
