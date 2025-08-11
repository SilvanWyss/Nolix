package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdata.model.IAbstractBackReference;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class BaseReferenceUpdater {

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private BaseReferenceUpdater() {
  }

  public static <E extends IEntity> void ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(
    final IAbstractReference<E> baseReference,
    final E entity) {

    final var baseBackReference = //
    ENTITY_SEARCHER.getOptionalStoredAbstractBackReferenceThatCanBackReferenceAbstractReference(
      entity,
      baseReference);

    baseBackReference.ifPresent(bbr -> toBaseBackReferenceAddOrSetEntity(bbr, baseReference.getStoredParentEntity()));
  }

  private static void toBaseBackReferenceAddOrSetEntity(
    final IAbstractBackReference<?> baseBackReference,
    final IEntity entity) {

    final var type = baseBackReference.getType();

    switch (type) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) baseBackReference;
        backReference.internalSetDirectlyBackReferencedEntityId(entity.getId());
        backReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) baseBackReference;
        optionalBackReference.internalSetDirectlyBackReferencedEntityId(entity.getId());
        optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case MULTI_BACK_REFERENCE:
        final var multiBackReference = (MultiBackReference<?>) baseBackReference;
        multiBackReference.internalAddBackReferencedEntityId(entity.getId());
        multiBackReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      default:
        throw InvalidArgumentException.forArgument(type);
    }
  }
}
