package ch.nolix.system.middata.schemaviewmapper;

import ch.nolix.systemapi.middataapi.schemaviewmapperapi.IColumnSchemaViewDtoMapper;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnSchemaViewDtoMapper implements IColumnSchemaViewDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto mapColumnDtoToColumnSchemaViewDto(
    final ColumnDto columnDto,
    final int oneBasedColumnOrdinalIndex) {

    final var id = columnDto.id();
    final var name = columnDto.name();
    final var contentModel = columnDto.contentModel();
    final var contentType = contentModel.getContentType();
    final var dataType = contentModel.getDataType();

    return new ColumnSchemaViewDto(id, name, oneBasedColumnOrdinalIndex, contentType, dataType);
  }
}
