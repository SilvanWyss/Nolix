package ch.nolix.systemapi.majorschemaapi.modelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;

public interface IColumnDtoMapper {

  IContainer<ColumnDto> mapMidSchemaColumnDtosToColumnDtos(
    IContainer<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> midSchemaColumnDto);
}
