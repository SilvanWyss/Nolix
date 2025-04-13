package ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;

public interface IMidSchemaColumnDtoMapper {

  IContainer<ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto> mapColumnDtoToMidSchemaColumnDtos(
    ColumnDto columnDto);
}
