/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
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
