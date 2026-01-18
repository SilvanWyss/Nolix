/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IBaseValueField;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

public abstract class AbstractBaseValueField<V> extends AbstractField implements IBaseValueField<V> {
  private final Class<V> valueType;

  protected AbstractBaseValueField(final Class<V> valueType) {
    Validator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<IBaseReference> getStoredBackReferencedBaseReferences() {
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
  public final boolean referencesBackField(final IField field) {
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
    //Does nothing.
  }
}
