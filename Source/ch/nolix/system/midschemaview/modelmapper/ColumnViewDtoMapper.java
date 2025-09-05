package ch.nolix.system.midschemaview.modelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.modelmapper.IColumnViewDtoMapper;

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
    final var fieldType = contentModel.getFieldType();
    final var dataType = contentModel.getDataType();

    return new ColumnViewDto(id, name, oneBasedColumnOrdinalIndex, fieldType, dataType);
  }
}
