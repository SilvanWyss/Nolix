package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public abstract class Entity //NOSONAR: A class called 'Entity' is commonly expected to be abstract.
extends AbstractEntity {

  private static final EntityFieldExtractor ENTITY_FIELD_EXTRACTOR = new EntityFieldExtractor();

  @Override
  public final String getParentTableName() {
    return getClass().getSimpleName();
  }

  @Override
  final IContainer<AbstractField> internalLoadFields() {
    return ENTITY_FIELD_EXTRACTOR.extractStoredFieldsFromEntity(this);
  }
}
