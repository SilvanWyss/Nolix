package ch.nolix.system.majorschema.modelmapper;

import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.majorschemaapi.modelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.majorschemaapi.modelmapperapi.ITableDtoMapper;

public final class TableDtoMapper implements ITableDtoMapper {

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  @Override
  public TableDto mapMidSchemaTableDtoToTableDto(
    final ch.nolix.systemapi.midschemaapi.modelapi.TableDto midSchemaTableDto) {

    final var id = midSchemaTableDto.id();
    final var name = midSchemaTableDto.name();
    final var midSchemaColumns = midSchemaTableDto.columns();
    final var columns = COLUMN_DTO_MAPPER.mapMidSchemaColumnDtosToColumnDtos(midSchemaColumns);

    return new TableDto(id, name, columns);
  }
}
