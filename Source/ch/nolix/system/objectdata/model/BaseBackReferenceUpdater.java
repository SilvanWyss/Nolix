package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;

public final class BaseBackReferenceUpdater {

  private BaseBackReferenceUpdater() {
  }

  public static void updateBaseBackReferenceForClearBaseReference(
    final IBaseBackReference<IEntity> baseBackReferene) {
    switch (baseBackReferene.getType()) {
      case BACK_REFERENCE:
        final var backReference = (BackReference<?>) baseBackReferene;
        backReference.internalClear();
        backReference.setAsEditedAndRunPotentialUpdateAction();
        break;
      case OPTIONAL_BACK_REFERENCE:
        final var optionalBackReference = (OptionalBackReference<?>) baseBackReferene;
        optionalBackReference.internalClear();
        optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
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
}
