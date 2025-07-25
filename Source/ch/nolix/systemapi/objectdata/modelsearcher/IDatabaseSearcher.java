package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IDatabaseSearcher {

  /**
   * @param database
   * @return the {@link IEntity}s of the given database in the local data.
   */
  IContainer<IEntity> getStoredEntitiesInLocalData(IDatabase database);
}
