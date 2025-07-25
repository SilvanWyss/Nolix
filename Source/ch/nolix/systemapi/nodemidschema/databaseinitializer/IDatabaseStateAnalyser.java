package ch.nolix.systemapi.nodemidschema.databaseinitializer;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.databaseinitializer.DatabaseState;

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
