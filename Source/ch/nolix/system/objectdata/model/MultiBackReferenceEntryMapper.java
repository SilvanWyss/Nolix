/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceEntryMapper {

  private MultiBackReferenceEntryMapper() {
  }

  /**
   * @param multiBackReferenceEntryDto
   * @param multiBackReference
   * @param <E>                        the type of the {@link Entity}s the given
   *                                   multiBackReference can reference back
   * @return a new loaded {@link MultiBackReferenceEntry} for the given
   *         multiBackReference from the given multiBackReferenceEntryDto
   */
  public static <E extends Entity> MultiBackReferenceEntry<E> //
  mapMultiBackReferenceEntryDtoToLoadedMultiBackReferenceEntry(
    final MultiBackReferenceEntryDto multiBackReferenceEntryDto, final MultiBackReference<E> multiBackReference) {
    final var backReferencedEntityId = multiBackReferenceEntryDto.backReferencedEntityId();
    final var backReferencedTableId = multiBackReferenceEntryDto.backReferencedEntityTableId();

    return //
    MultiBackReferenceEntry
      .createLoadedEntryForMultiBackReferenceAndBackReferencedEntityIdAndBackReferencedTableId(
        multiBackReference,
        backReferencedEntityId,
        backReferencedTableId);
  }
}
