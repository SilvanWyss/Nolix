package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public abstract class Entity extends BaseEntity {

  private static final FieldFromEntityMapper FIELD_FROM_ENTITY_MAPPER = new FieldFromEntityMapper();

  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  @Override
  final IContainer<Field> internalLoadFields() {
    return FIELD_FROM_ENTITY_MAPPER.getStoredFieldsFrom(this);
  }
}
