/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceEntryMapper {

  private MultiBackReferenceEntryMapper() {
  }

  public static <E extends IEntity> MultiBackReferenceEntry<E> //
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
