package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IMultiReferenceEntryDeletionDtoMapper {

  /**
   * @param multiReferenceEntry
   * @return a new {@link MultiReferenceEntryDeletionDto} from the given
   *         multiReferenceEntry.
   * @throws RuntimeException if the given multiReferenceEntry is null.
   */
  MultiReferenceEntryDeletionDto mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
    IMultiReferenceEntry<? extends IEntity> multiReferenceEntry);
}
