/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueEntryDtoMapper {
  /**
   * @param multiValueEntry
   * @return a new {@link MultiValueEntryDto} from the given multiValueEntry.
   * @throws RuntimeException if the given multiValueEntry is null.
   */
  MultiValueEntryDto mapMultiValueEntryToMultiValueEntryDto(IMultiValueFieldEntry<?> multiValueEntry);
}
