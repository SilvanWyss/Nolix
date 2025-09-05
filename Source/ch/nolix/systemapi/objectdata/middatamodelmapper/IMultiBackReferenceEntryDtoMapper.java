package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IMultiBackReferenceEntryDtoMapper {
  /**
   * @param multiBackReferenceEntry
   * @return a new {@link MultiBackReferenceEntryDto} from the given
   *         multiBackReferenceEntry.
   * @throws RuntimeException if the given multiBackReferenceEntry is null.
   */
  MultiBackReferenceEntryDto mapMultiBackReferenceEntryToMultiBackReferenceEntryDto(
    IMultiBackReferenceEntry<? extends IEntity> multiBackReferenceEntry);
}
