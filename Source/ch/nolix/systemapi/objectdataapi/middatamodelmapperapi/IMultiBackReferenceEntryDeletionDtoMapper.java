package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
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
