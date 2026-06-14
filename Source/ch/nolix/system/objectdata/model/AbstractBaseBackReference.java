/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractBaseBackReference extends AbstractField implements IBaseBackReference {
  private final ImmutableList<String> backReferenceableTableNames;

  private final String backReferencedFieldName;

  protected AbstractBaseBackReference(
    final IWellOrderContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {

    Validator.assertThatTheStrings(backReferenceableTableNames).areNotBlank();

    Validator
      .assertThat(backReferencedFieldName)
      .thatIsNamed("back referenced field name")
      .isNotBlank();

    this.backReferenceableTableNames = ImmutableList.fromIterable(backReferenceableTableNames);
    this.backReferencedFieldName = backReferencedFieldName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IWellOrderContainer<String> getBackReferenceableTableNames() {
    return backReferenceableTableNames;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getBackReferencedFieldName() {
    return backReferencedFieldName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IWellOrderContainer<IBaseBackReference> getStoredBaseBackReferencesWhoReferencesBackThis() {
    return ImmutableList.createEmpty();
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
