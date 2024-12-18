package ch.nolix.system.objectschema.rawschemadtomapper;

import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  @Override
  public ColumnDto mapColumnToColumnDto(final IColumn column) {

    final var id = column.getId();
    final var name = column.getName();
    final var contentModel = column.getContentModel();
    final var contentModelDto = contentModel.toDto();

    return new ColumnDto(id, name, contentModelDto);
  }
}
