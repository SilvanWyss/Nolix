package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;

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
