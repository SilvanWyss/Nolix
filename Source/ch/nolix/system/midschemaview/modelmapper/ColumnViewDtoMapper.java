package ch.nolix.system.midschemaview.modelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.modelmapper.IColumnViewDtoMapper;

/**
 * @author Silvan Wyss
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
    final var fieldType = columnDto.fieldType();
    final var dataType = columnDto.dataType();

    return new ColumnViewDto(id, name, oneBasedColumnOrdinalIndex, fieldType, dataType);
  }
}
