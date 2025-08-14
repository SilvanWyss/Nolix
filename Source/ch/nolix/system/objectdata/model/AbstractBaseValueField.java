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

  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final IContainer<IBaseReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final Class<V> getValueType() {
    return valueType;
  }

  @Override
  public final IContainer<IField> internalGetStoredSubFields() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
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

  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  @Override
  protected final void internalUpdateBackReferencingFieldsWhenIsInsertedIntoDatabase() {
    //Does nothing.
  }
}
