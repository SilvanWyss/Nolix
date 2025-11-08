package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class BaseBackReferenceUpdater {
  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private BaseBackReferenceUpdater() {
  }

  public static void updateBaseBackReferenceForClearBaseReference(
    final IBaseBackReference baseBackReference,
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
    final IBaseBackReference baseBackReference,
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
