package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public final class BaseReferenceUpdater {
  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private BaseReferenceUpdater() {
  }

  public static <E extends IEntity> void ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(
    final IBaseReference baseReference,
    final E entity) {
    final var baseBackReference = //
    ENTITY_SEARCHER.getOptionalStoredBaseBackReferenceWhoCanBackReferenceTheBaseReference(
      entity,
      baseReference);

    baseBackReference.ifPresent(bbr -> toBaseBackReferenceAddOrSetEntity(bbr, baseReference.getStoredParentEntity()));
  }

  private static void toBaseBackReferenceAddOrSetEntity(
    final IBaseBackReference<?> baseBackReference,
    final IEntity entity) {
    final var type = baseBackReference.getType();

    switch (type) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) baseBackReference;
        backReference.setBackReferencedEntityOnly(entity);
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) baseBackReference;
        optionalBackReference.setBackReferencedEntityOnly(entity);
        break;
      case MULTI_BACK_REFERENCE:
        final var multiBackReference = (MultiBackReference<?>) baseBackReference;
        multiBackReference.internalAddBackReferencedEntity(entity);
        break;
      default:
        throw InvalidArgumentException.forArgument(type);
    }
  }
}
