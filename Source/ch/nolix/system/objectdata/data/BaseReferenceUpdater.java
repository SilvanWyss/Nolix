//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
public final class BaseReferenceUpdater {

  //constant
  private static final EntityTool ENTITY_TOOL = new EntityTool();

  //method
  public <E extends IEntity> void ofBaseReferenceUpdatePotentialBaseBackReferenceForAddOrSetEntity(
    final IBaseReference<E> baseReference,
    final E entity) {

    final var baseBackReference = //
    ENTITY_TOOL.getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(entity, baseReference);

    baseBackReference.ifPresent(bbr -> toBaseBackReferenceAddOrSetEntity(bbr, baseReference.getStoredParentEntity()));
  }

  //method
  private void toBaseBackReferenceAddOrSetEntity(final IBaseBackReference<?> baseBackReference, final IEntity entity) {

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
