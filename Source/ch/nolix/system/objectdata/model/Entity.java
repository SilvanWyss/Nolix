package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.entitytool.EntityFieldExtractor;
import ch.nolix.system.objectdata.entitytool.TableNameExtractor;
import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;

public abstract class Entity //NOSONAR: An entity class is expected to be abstract.
extends AbstractEntity {
  private static final ITableNameExtractor TABLE_NAME_EXTRACTOR = new TableNameExtractor();

  private static final EntityFieldExtractor ENTITY_FIELD_EXTRACTOR = new EntityFieldExtractor();

  @Override
  public final String getParentTableName() {
    return TABLE_NAME_EXTRACTOR.getTableNameOfEntity(this);
  }

  @Override
  final IContainer<AbstractField> findFields() {
    return ENTITY_FIELD_EXTRACTOR.extractStoredFieldsFromEntity(this);
  }
}
