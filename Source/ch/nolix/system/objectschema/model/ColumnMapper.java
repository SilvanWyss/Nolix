package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionPatternCatalog;
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

    final var midColumnDtoGroups = //
    midColumns.getStoredInGroups(c -> RegularExpressionPatternCatalog.DOLLAR_PATTERN.split(c.name())[0]);

    return midColumnDtoGroups.to(g -> mapMidColumnDtosToColumn(g, tables));
  }

  private static Column mapMidColumnDtosToColumn(
    final IContainer<ColumnDto> midColumnDtos,
    final IContainer<ITable> tables) {

    final var firstMidColumnDto = midColumnDtos.getStoredFirst();
    final var id = firstMidColumnDto.id();
    final var name = firstMidColumnDto.name();
    final var midContentModels = midColumnDtos.to(ColumnDto::contentModel);

    final var contentModels = //
    midContentModels.to(c -> ContentModelMapper.mapMidContentModelDtoToContentModel(c, tables));

    return Column.withIdAndNameAndContentModels(id, name, contentModels);
  }
}
