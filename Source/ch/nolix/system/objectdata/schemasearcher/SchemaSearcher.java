package ch.nolix.system.objectdata.schemasearcher;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.schemamodelsearcher.ISchemaSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class SchemaSearcher implements ISchemaSearcher {

  /***
   * {@inheritDoc}
   */
  @Override
  public Class<? extends IEntity> getEntityTypeByName(final IEntityTypeSet entityTypeSet, final String name) {
    return entityTypeSet.getEntityTypes().getStoredFirst(t -> t.getSimpleName().equals(name));
  }
}
