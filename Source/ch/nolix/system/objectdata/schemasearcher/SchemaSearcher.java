package ch.nolix.system.objectdata.schemasearcher;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.schemamodelsearcher.ISchemaSearcher;

/**
 * @author Silvan Wyss
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
