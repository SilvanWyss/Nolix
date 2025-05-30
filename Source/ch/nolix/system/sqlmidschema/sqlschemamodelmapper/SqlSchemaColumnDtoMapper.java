package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi.ISqlSchemaColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public final class SqlSchemaColumnDtoMapper implements ISqlSchemaColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(
    final ColumnDto columnDto) {

    final IArrayList<ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto> sqlSchemaColumnDtos = //
    ArrayList.withInitialCapacity(2);

    final var mainSqlSchemaColumnDto = SqlSchemaColumnDtoMapperHelper.mapColumnDtoToMainSqlSchemaColumnDto(columnDto);

    sqlSchemaColumnDtos.addAtEnd(mainSqlSchemaColumnDto);

    if (columnDto.contentModel().getContentType().getBaseType() == BaseContentType.BASE_REFERENCE) {

      final var referenceSqlSchemaColumnDto = //
      SqlSchemaColumnDtoMapperHelper.mapColumnDtoToReferenceSqlSchemaColumnDto(columnDto);

      sqlSchemaColumnDtos.addAtEnd(referenceSqlSchemaColumnDto);
    }

    return sqlSchemaColumnDtos;
  }
}
