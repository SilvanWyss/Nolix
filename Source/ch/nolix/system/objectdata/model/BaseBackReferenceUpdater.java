/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class BaseBackReferenceUpdater {
  private static final EntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private BaseBackReferenceUpdater() {
  }

  public static void updateBaseBackReferenceForClearBaseReference(
    final BaseBackReference baseBackReference,
    final String backReferencedEntityId) {
    switch (baseBackReference) {
      case BackReference<? extends IEntity> backReference ->
        backReference.clear();
      case OptionalBackReference<? extends IEntity> optionalBackReference ->
        optionalBackReference.clear();
      case MultiBackReference<? extends IEntity> multiBackReference ->
        multiBackReference.deleteEntryByBackReferencedEntityId(backReferencedEntityId);
      default ->
        throw InvalidArgumentException.forArgument(baseBackReference);
    }
  }

  public static void updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(
    final BaseBackReference baseBackReference,
    final IEntity entity) {
    switch (baseBackReference) {
      case BackReference<? extends IEntity> backReference ->
        backReference.clear();
      case OptionalBackReference<? extends IEntity> optionalBackReference ->
        optionalBackReference.clear();
      case MultiBackReference<? extends IEntity> multiBackReference -> {
        final var backReferencedEntityId = entity.getId();
        multiBackReference.deleteEntryByBackReferencedEntityId(backReferencedEntityId);
      }
      default ->
        throw InvalidArgumentException.forArgument(baseBackReference);
    }
  }

  public static void updateBaseBackReferencesThatReferencesBackEntityForDeleteEntity(final IEntity entity) {
    final var baseBackReferences = ENTITY_SEARCHER.getStoredBaseBackReferencesThatReferenceBackEntity(entity);

    for (final var b : baseBackReferences) {
      updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(b, entity);
    }
  }
}
