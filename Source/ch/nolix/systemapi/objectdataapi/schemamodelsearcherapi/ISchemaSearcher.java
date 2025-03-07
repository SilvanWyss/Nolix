package ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface ISchemaSearcher {

  /**
   * @param name
   * @param entityTypeSet
   * @return the entity type with the given name from the given schema.
   * @throws RuntimeException if the given schema does not contain an entity type
   *                          with the given name.
   */
  Class<? extends IEntity> getEntityTypeByName(IEntityTypeSet entityTypeSet, String name);
}
