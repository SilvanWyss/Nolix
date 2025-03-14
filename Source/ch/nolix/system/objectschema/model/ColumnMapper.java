package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionPatternCatalog;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public final class ColumnMapper {

  private ColumnMapper() {
  }

  public static IContainer<Column> mapRawTableDtoToColumns(
    final TableDto rawTableDto,
    final IContainer<ITable> tables) {

    final var rawColumns = rawTableDto.columns();

    final var rawColumnDtoGroups = //
    rawColumns.getStoredInGroups(c -> RegularExpressionPatternCatalog.DOLLAR_PATTERN.split(c.name())[0]);

    return rawColumnDtoGroups.to(g -> mapRawColumnDtosToColumn(g, tables));
  }

  private static Column mapRawColumnDtosToColumn(
    final IContainer<ColumnDto> rawColumnDtos,
    final IContainer<ITable> tables) {

    final var firstRawColumnDto = rawColumnDtos.getStoredFirst();
    final var id = firstRawColumnDto.id();
    final var name = firstRawColumnDto.name();
    final var rawConentModels = rawColumnDtos.to(ColumnDto::contentModel);

    final var contentModels = //
    rawConentModels.to(c -> ContentModelMapper.mapRawContentModelDtoToContentModel(c, tables));

    return Column.withIdAndNameAndContentModels(id, name, contentModels);
  }
}
