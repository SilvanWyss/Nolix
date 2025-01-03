package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public abstract class Entity extends AbstractEntity {

  private static final FieldFromEntityMapper FIELD_FROM_ENTITY_MAPPER = new FieldFromEntityMapper();

  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  @Override
  final IContainer<AbstractField> internalLoadFields() {
    return FIELD_FROM_ENTITY_MAPPER.getStoredFieldsFrom(this);
  }
}
