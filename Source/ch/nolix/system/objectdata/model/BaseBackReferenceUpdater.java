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

  public static void updateBaseBackReferenceForClearBaseReference(final IBaseBackReference baseBackReferene) {
    switch (baseBackReferene.getType()) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) baseBackReferene;
        backReference.clear();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) baseBackReferene;
        optionalBackReference.clear();
        break;
      case MULTI_BACK_REFERENCE:
        /*
         * Does nothing. MultiBackReferences do not need to be updated, because
         * MultiBackReferences do not have redundancies.
         */
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(baseBackReferene, "back referencing field");
      //Does nothing.
    }
  }

  public static void updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(
    final IBaseBackReference baseBackReference,
    final IEntity entity) {
    switch (baseBackReference) {
      case BackReference<? extends IEntity> backReference -> {
        backReference.clear();
        backReference.setAsEditedAndRunPossibleUpdateAction();
      }
      case OptionalBackReference<? extends IEntity> optionalBackReference -> {
        optionalBackReference.clear();
        optionalBackReference.setAsEditedAndRunPossibleUpdateAction();
      }
      case MultiBackReference<? extends IEntity> multiBackReference -> {
        final var backReferencedEntityId = entity.getId();
        multiBackReference.deleteEntryByBackReferencedEntityId(backReferencedEntityId);
      }
      default -> throw InvalidArgumentException.forArgument(baseBackReference);
    }
  }

  public static void updateBaseBackReferencesThatReferencesBackEntityForDeleteEntity(final IEntity entity) {
    final var baseBackReferences = ENTITY_SEARCHER.getStoredBaseBackReferencesThatReferenceBackEntity(entity);

    for (final var b : baseBackReferences) {
      updateBaseBackReferenceThatReferencesBackEntityForDeleteEntity(b, entity);
    }
  }
}
