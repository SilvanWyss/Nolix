package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

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
