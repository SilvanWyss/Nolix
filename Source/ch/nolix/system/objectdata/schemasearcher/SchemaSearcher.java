package ch.nolix.system.objectdata.schemasearcher;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi.ISchemaSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class SchemaSearcher implements ISchemaSearcher {

  /***
   * {@inheritDoc}
   */
  @Override
  public Class<? extends IEntity> getEntityTypeByName(final ISchema schema, final String name) {
    return schema.getEntityTypes().getStoredFirst(t -> t.getSimpleName().equals(name));
  }
}
