package ch.nolix.systemapi.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface INodeDatabaseExaminer {
  /**
   * @param nodeDatabase
   * @return true if the given nodeDatabase is properly (!) initialized, false
   *         otherwise.
   */
  boolean nodeDatabaseIsInitialized(IMutableNode<?> nodeDatabase);

  /**
   * @param nodeDatabase
   * @return true if the given nodeDatabase is properly (!) uninitialized, false
   *         otherwise.
   */
  boolean nodeDatabaseIsUninitialized(IMutableNode<?> nodeDatabase);
}
