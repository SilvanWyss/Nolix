package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

public abstract class BaseValue<V> extends Field implements IBaseValue<V> {

  private final Class<V> valueType;

  protected BaseValue(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final IContainer<IField> getStoredReferencingFields() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final Class<V> getValueType() {
    return valueType;
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
  public boolean referencesBackField(final IField field) {
    return false;
  }

  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  @Override
  final void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    //Does nothing.
  }
}
