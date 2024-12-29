package ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface ISchemaSearcher {

  /**
   * @param name
   * @param schema
   * @return the entity type with the given name from the given schema.
   * @throws RuntimeException if the given schema is not valid.
   * @throws RuntimeException if the given schema does not contain an entity type
   *                          with the given name.
   */
  Class<? extends IEntity> getEntityTypeByName(ISchema schema, String name);
}
