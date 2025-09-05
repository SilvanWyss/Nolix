package ch.nolix.system.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiValueEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public final class MultiValueEntryDtoMapper implements IMultiValueEntryDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public MultiValueEntryDto mapMultiValueEntryToMultiValueEntryDto(final IMultiValueFieldEntry<?> multiValueEntry) {
    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiValue = multiValueEntry.getStoredParentMultiValue();
    final var multiValueColumn = multiValue.getStoredParentColumn();
    final var multiValueColumnId = multiValueColumn.getId();
    final var value = multiValueEntry.getStoredValue().toString();

    return new MultiValueEntryDto(tableName, entityId, multiValueColumnId, value);
  }
}
