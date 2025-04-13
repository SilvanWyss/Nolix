package ch.nolix.systemapi.majorschemaapi.modelmapperapi;

import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;

public interface ITableDtoMapper {

  TableDto mapMidSchemaTableDtoToTableDto(final ch.nolix.systemapi.midschemaapi.modelapi.TableDto midSchemaTableDto);
}
