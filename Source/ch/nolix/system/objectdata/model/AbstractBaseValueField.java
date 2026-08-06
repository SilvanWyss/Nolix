/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.BaseValueField;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link AbstractBaseValueField}.
 */
public abstract class AbstractBaseValueField<V> extends AbstractField implements BaseValueField<V> {
  private final Class<V> valueType;

  protected AbstractBaseValueField(final Class<V> valueType) {
    Validator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<BaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    return ImmutableList.createEmpty();
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
  public final Class<V> getValueType() {
    return valueType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void noteInsertIntoDatabase() {
    // Does nothing.
  }
}
