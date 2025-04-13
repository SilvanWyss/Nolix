package ch.nolix.system.majorschema.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionStringPatternCatalog;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;

public final class ColumnDtoMapperHelper {

  private ColumnDtoMapperHelper() {
  }

  public static ColumnDto mapMidSchemaColumnDtosToColumnDto(
    final IContainer<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> midSchemaColumnDtos) {

    final var firstMidSchemaColumn = midSchemaColumnDtos.getStoredFirst();
    final var id = firstMidSchemaColumn.id();
    final var name = firstMidSchemaColumn.name().split(RegularExpressionStringPatternCatalog.DOLLAR_PATTERN)[0];
    final var contentModels = midSchemaColumnDtos.to(ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto::contentModel);

    return new ColumnDto(id, name, contentModels);
  }
}
