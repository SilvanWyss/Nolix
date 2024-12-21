package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodelmapper.ContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

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
