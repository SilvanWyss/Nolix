package ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IDatabaseStateAnalyser {

  /**
   * @param nodeDatabase
   * @return the state of the given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  DatabaseState getStateOfNodeDatabase(IMutableNode<?> nodeDatabase);
}
