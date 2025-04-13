package ch.nolix.system.majorschema.midschemamodelmapper;

import ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi.IMidSchemaColumnDtoMapper;
import ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi.IMidSchemaTableDtoMapper;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

public final class MidSchemaTableDtoMapper implements IMidSchemaTableDtoMapper {

  private static final IMidSchemaColumnDtoMapper MID_SCHEMA_COLUMN_DTO_MAPPER = new MidSchemaColumnDtoMapper();

  @Override
  public TableDto mapTableDtoToMidSchemaTableDto(final ch.nolix.systemapi.majorschemaapi.modelapi.TableDto tableDto) {

    final var id = tableDto.id();
    final var name = tableDto.name();
    final var columns = tableDto.columns();
    final var midSchemaColumns = columns.toMultiples(MID_SCHEMA_COLUMN_DTO_MAPPER::mapColumnDtoToMidSchemaColumnDtos);

    return new TableDto(id, name, midSchemaColumns);
  }
}
