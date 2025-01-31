package ch.nolix.system.rawdata.schemaviewmapper;

import ch.nolix.systemapi.rawdataapi.schemaviewmapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnViewDtoMapper implements IColumnViewDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto mapColumnDtoToColumnViewDto(final ColumnDto columnDto, final int oneBasedColumnOrdinalIndex) {

    final var id = columnDto.id();
    final var name = columnDto.name();
    final var contentModel = columnDto.contentModel();
    final var contentType = contentModel.getContentType();
    final var dataType = contentModel.getDataType();

    return new ColumnSchemaViewDto(id, name, oneBasedColumnOrdinalIndex, contentType, dataType);
  }
}
