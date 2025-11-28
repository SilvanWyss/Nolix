package ch.nolix.system.sqlmiddata.sqlmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.sqlmiddata.sqlmapper.ISqlPartsMapper;

/**
 * @author Silvan Wyss
 * @version 2025-11-28
 */
public final class SqlPartsMapper implements ISqlPartsMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> mapValueStringFieldDtoToColumnNames(final ValueStringFieldDto valueStringFieldDto) {
    final var columnName = valueStringFieldDto.columnName();

    if (valueStringFieldDto.nullableAdditionalValue() != null) {
      final var additionalColumnName = columnName + "Table";

      return ImmutableList.withElements(columnName, additionalColumnName);
    }

    return ImmutableList.withElements(columnName);
  }
}
