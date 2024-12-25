package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

public abstract class AbstractValue<V> extends AbstractField implements IAbstractValue<V> {

  private final Class<V> valueType;

  protected AbstractValue(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed("value type").isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences() {
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
