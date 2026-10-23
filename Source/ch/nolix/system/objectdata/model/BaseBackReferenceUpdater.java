/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public final class BaseBackReferenceUpdater {
  private static final EntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private BaseBackReferenceUpdater() {
  }

  /**
   * Updates the given baseBackReference for the case that a BaseReference that
   * references the {@link Entity} with the given backReferencedEntityId is
   * cleared.
   * 
   * @param baseBackReference
   * @param backReferencedEntityId
   * @throws RuntimeException if the given baseBackReference is not a
   *                          {@link BackReference}, {@link OptionalBackReference}
   *                          or {@link MultiBackReference}
   */
  public static void updateBaseBackReferenceForClearBaseReference(
    final BaseBackReference baseBackReference,
    final String backReferencedEntityId) {
    switch (baseBackReference) {
      case BackReference<? extends Entity> backReference ->
        backReference.clear();
      case OptionalBackReference<? extends Entity> optionalBackReference ->
        optionalBackReference.clear();
      case MultiBackReference<? extends Entity> multiBackReference ->
        multiBackReference.deleteEntryByBackReferencedEntityId(backReferencedEntityId);
      default ->
        throw InvalidArgumentException.forArgument(baseBackReference);
    }
  }

  /**
   * Updates the given baseBackReference, that references back the given entity,
   * for the case that the given entity is deleted.
   * 
   * @param baseBackReference
   * @param entity
   * @throws RuntimeException if the given baseBackReference is not a
   *                          {@link BackReference}, {@link OptionalBackReference}
   *                          or {@link MultiBackReference}
   */
  public static void updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(
    final BaseBackReference baseBackReference,
    final Entity entity) {
    switch (baseBackReference) {
      case BackReference<? extends Entity> backReference ->
        backReference.clear();
      case OptionalBackReference<? extends Entity> optionalBackReference ->
        optionalBackReference.clear();
      case MultiBackReference<? extends Entity> multiBackReference -> {
        final var backReferencedEntityId = entity.getId();
        multiBackReference.deleteEntryByBackReferencedEntityId(backReferencedEntityId);
      }
      default ->
        throw InvalidArgumentException.forArgument(baseBackReference);
    }
  }

  /**
   * Updates the {@link BaseBackReference}s that reference back the given entity
   * for the case that the given entity is deleted.
   * 
   * @param entity
   */
  public static void updateBaseBackReferencesThatReferencesBackEntityForDeleteEntity(final Entity entity) {
    final var baseBackReferences = ENTITY_SEARCHER.getStoredBaseBackReferencesThatReferenceBackEntity(entity);

    for (final var b : baseBackReferences) {
      updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(b, entity);
    }
  }
}
