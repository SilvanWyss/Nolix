package ch.nolix.system.midschemaview.modelmapper;

import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelmapperapi.IColumnViewDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnViewDtoMapper implements IColumnViewDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto mapMidSchemaColumnDtoToColumnViewDto(
    final ColumnDto columnDto,
    final int oneBasedColumnOrdinalIndex) {

    final var id = columnDto.id();
    final var name = columnDto.name();
    final var contentModel = columnDto.contentModel();
    final var contentType = contentModel.getContentType();
    final var dataType = contentModel.getDataType();

    return new ColumnViewDto(id, name, oneBasedColumnOrdinalIndex, contentType, dataType);
  }
}
