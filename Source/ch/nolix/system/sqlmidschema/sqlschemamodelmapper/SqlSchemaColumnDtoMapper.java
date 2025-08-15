package ch.nolix.system.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper.ISqlSchemaColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public final class SqlSchemaColumnDtoMapper implements ISqlSchemaColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ch.nolix.systemapi.sqlschema.model.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(
    final ColumnDto columnDto) {

    final IArrayList<ch.nolix.systemapi.sqlschema.model.ColumnDto> sqlSchemaColumnDtos = //
    ArrayList.withInitialCapacity(2);

    final var mainSqlSchemaColumnDto = SqlSchemaColumnDtoMapperHelper.mapColumnDtoToMainSqlSchemaColumnDto(columnDto);

    sqlSchemaColumnDtos.addAtEnd(mainSqlSchemaColumnDto);

    if (columnDto.contentModel().getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE) {

      final var referenceSqlSchemaColumnDto = //
      SqlSchemaColumnDtoMapperHelper.mapColumnDtoToReferenceSqlSchemaColumnDto(columnDto);

      sqlSchemaColumnDtos.addAtEnd(referenceSqlSchemaColumnDto);
    }

    return sqlSchemaColumnDtos;
  }
}
