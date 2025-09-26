package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

public abstract class AbstractBaseReference<E extends IEntity> extends AbstractField implements IBaseReference {
  private final ImmutableList<String> referenceableTableNames;

  protected AbstractBaseReference(final IContainer<String> referenceableTableNames) {
    Validator.assertThatTheStrings(referenceableTableNames).areNotBlank();
    this.referenceableTableNames = ImmutableList.forIterable(referenceableTableNames);
  }

  @Override
  public final IContainer<String> getReferenceableTableNames() {
    return referenceableTableNames;
  }

  @Override
  public final IContainer<IBaseReference> getStoredBaseReferencesWhoAreBackReferencedFromThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  @Override
  public final boolean referencesBackEntityWithId(final String id) {
    return false;
  }

  @Override
  public final boolean referencesBackField(final IField field) {
    return false;
  }

  protected final void updateProbableBackReferenceForSetOrAddedEntity(final E entity) {
    for (final var p : entity.internalGetStoredFields()) {
      switch (p.getType()) {
        case BACK_REFERENCE:

          final var backReference = (BackReference<?>) p;

          if (backReference.referencesBackField(this)) {
            backReference.setBackReferencedEntityOnly(getStoredParentEntity());
          }

          break;

        case OPTIONAL_BACK_REFERENCE:

          final var optionalBackReference = (OptionalBackReference<?>) p;

          if (optionalBackReference.referencesBackField(this)) {
            optionalBackReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
          }

          break;
        default:
          //Does nothing.
      }
    }
  }
}
