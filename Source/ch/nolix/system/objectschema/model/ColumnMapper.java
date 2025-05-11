package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ColumnMapper {

  private ColumnMapper() {
  }

  public static IContainer<Column> mapMidTableDtoToColumns(
    final TableDto midTableDto,
    final IContainer<ITable> tables) {

    final var midColumns = midTableDto.columns();

    return midColumns.to(c -> mapMidColumnDtoToColumn(c, tables));
  }

  private static Column mapMidColumnDtoToColumn(
    final ColumnDto midColumnDto,
    final IContainer<ITable> tables) {

    final var id = midColumnDto.id();
    final var name = midColumnDto.name();
    final var midContentModel = midColumnDto.contentModel();

    final var contentModel = ContentModelMapper.mapMidContentModelDtoToContentModel(midContentModel, tables);

    return Column.withIdAndNameAndContentModel(id, name, contentModel);
  }
}
