package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceEntryDeletionDtoMapper {
  /**
   * @param multiBackReferenceEntry
   * @return a new {@link MultiBackReferenceEntryDeletionDto} from the given
   *         multiBackReferenceEntry.
   * @throws RuntimeException if the given multiBackReferenceEntry is null.
   */
  MultiBackReferenceEntryDeletionDto mapMultiBackReferenceEntryToMultiBackReferenceEntryDeletionDto(
    IMultiBackReferenceEntry<? extends IEntity> multiBackReferenceEntry);
}
