package ch.nolix.system.majorschema.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionPatternCatalog;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelmapperapi.IColumnDtoMapper;

public final class ColumnDtoMapper implements IColumnDtoMapper {

  @Override
  public IContainer<ColumnDto> mapMidSchemaColumnDtosToColumnDtos(
    final IContainer<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> midSchemaColumnDto) {

    final var midSchemaColumnGroups = //
    midSchemaColumnDto.getStoredInGroups(c -> RegularExpressionPatternCatalog.DOLLAR_PATTERN.split(c.name())[0]);

    return midSchemaColumnGroups.to(ColumnDtoMapperHelper::mapMidSchemaColumnDtosToColumnDto);
  }
}
