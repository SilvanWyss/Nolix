package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IMultiReferenceEntryDtoMapper {
  /**
   * @param multiReferenceEntry
   * @return a new {@link MultiReferenceEntryDto} from the given
   *         multiReferenceEntry.
   * @throws RuntimeException if the given multiReferenceEntry is null.
   */
  MultiReferenceEntryDto mapMultiReferenceEntryToMultiReferenceEntryDto(
    IMultiReferenceEntry<? extends IEntity> multiReferenceEntry);
}
