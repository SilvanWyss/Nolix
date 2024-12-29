package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.modelmapper.ContentModelMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

final class ColumnMapper {

  private static final ContentModelMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ContentModelMapper();

  public IColumn createColumnFromDtoForParentTableUsingGivenReferencableTables(
    final ColumnDto columnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {
    return Column.withNameAndIdAndParameterizedFieldTypeAndParentTable(
      columnDto.name(),
      columnDto.id(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.mapContentModelDtoToContentModel(
        columnDto.contentModel(),
        referencableTables),
      parentTable);
  }
}
