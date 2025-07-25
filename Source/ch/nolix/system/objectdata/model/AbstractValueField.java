package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IAbstractBackReference;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IAbstractValueField;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

public abstract class AbstractValueField<V> extends AbstractField implements IAbstractValueField<V> {

  private final Class<V> valueType;

  protected AbstractValueField(final Class<V> valueType) {

    Validator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final IContainer<IAbstractReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis() {
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
