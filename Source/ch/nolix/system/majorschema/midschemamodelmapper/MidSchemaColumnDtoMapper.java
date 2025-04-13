package ch.nolix.system.majorschema.midschemamodelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programstructure.data.IdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.RegularExpressionPatternCatalog;
import ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi.IMidSchemaColumnDtoMapper;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;

public final class MidSchemaColumnDtoMapper implements IMidSchemaColumnDtoMapper {

  @Override
  public IContainer<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> mapColumnDtoToMidSchemaColumnDtos(
    final ColumnDto columnDto) {

    final ILinkedList<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> midSchemaColumns = LinkedList.createEmpty();
    final var contentModels = columnDto.contentModels();
    final var midSchemaColumnsNameFirstPart = RegularExpressionPatternCatalog.DOLLAR_PATTERN.split(columnDto.name())[0];
    var oneBasedIndex = 1;

    for (final var m : contentModels) {

      final var midSchemaColumnId = IdCreator.createIdOf10HexadecimalCharacters();
      final var midSchemaColumnName = midSchemaColumnsNameFirstPart + CharacterCatalog.DOLLAR + oneBasedIndex;

      final var midSchemaColumn = //
      new ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto(midSchemaColumnId, midSchemaColumnName, m);

      midSchemaColumns.addAtEnd(midSchemaColumn);
      oneBasedIndex++;
    }

    return midSchemaColumns;
  }
}
