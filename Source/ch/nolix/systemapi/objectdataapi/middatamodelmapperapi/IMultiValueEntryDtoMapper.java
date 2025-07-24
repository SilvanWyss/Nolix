package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueFieldEntry;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IMultiValueEntryDtoMapper {

  /**
   * @param multiValueEntry
   * @return a new {@link MultiValueEntryDto} from the given multiValueEntry.
   * @throws RuntimeException if the given multiValueEntry is null.
   */
  MultiValueEntryDto mapMultiValueEntryToMultiValueEntryDto(IMultiValueFieldEntry<?> multiValueEntry);
}
