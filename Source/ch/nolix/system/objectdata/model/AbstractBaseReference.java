/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s of a
 *            {@link AbstractBaseReference}.
 */
public abstract class AbstractBaseReference<E extends IEntity> extends AbstractField implements BaseReference {
  private final ImmutableList<String> referenceableTableNames;

  protected AbstractBaseReference(final ExtendedIterable<String> referenceableTableNames) {
    Validator.assertThatTheStrings(referenceableTableNames).areNotBlank();
    this.referenceableTableNames = ImmutableList.fromIterable(referenceableTableNames);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> getReferenceableTableNames() {
    return referenceableTableNames;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<BaseReference> getStoredBackReferencedBaseReferences() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean referencesBackEntityWithId(final String id) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean referencesBackField(final Field field) {
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
            optionalBackReference.setBackReferencedEntityOnly(getStoredParentEntity());
          }

          break;
        default:
          // Does nothing.
      }
    }
  }
}
