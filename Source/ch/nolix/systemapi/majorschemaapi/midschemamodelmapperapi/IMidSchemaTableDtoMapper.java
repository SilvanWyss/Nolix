package ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi;

import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;

public interface IMidSchemaTableDtoMapper {

  ch.nolix.systemapi.midschemaapi.modelapi.TableDto mapTableDtoToMidSchemaTableDto(TableDto tableDto);
}
