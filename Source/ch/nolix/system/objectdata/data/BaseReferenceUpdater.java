package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

public final class BaseReferenceUpdater {

  private static final EntityTool ENTITY_TOOL = new EntityTool();

  public <E extends IEntity> void ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(
    final IAbstractReference<E> baseReference,
    final E entity) {

    final var baseBackReference = //
    ENTITY_TOOL.getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(entity, baseReference);

    baseBackReference.ifPresent(bbr -> toBaseBackReferenceAddOrSetEntity(bbr, baseReference.getStoredParentEntity()));
  }

  private void toBaseBackReferenceAddOrSetEntity(final IAbstractBackReference<?> baseBackReference, final IEntity entity) {

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
