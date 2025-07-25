package ch.nolix.system.nodemidschema.databaseinitializer;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodeexaminer.NodeDatabaseExaminer;
import ch.nolix.systemapi.midschema.databaseinitializer.DatabaseState;
import ch.nolix.systemapi.nodemidschema.databaseinitializer.IDatabaseStateAnalyser;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.INodeDatabaseExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class DatabaseStateAnalyser implements IDatabaseStateAnalyser {

  private static final INodeDatabaseExaminer NODE_DATABASE_EXAMINER = new NodeDatabaseExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getStateOfNodeDatabase(final IMutableNode<?> nodeDatabase) {

    if (NODE_DATABASE_EXAMINER.nodeDatabaseIsUninitialized(nodeDatabase)) {
      return DatabaseState.UNINITIALIZED;
    }

    if (NODE_DATABASE_EXAMINER.nodeDatabaseIsInitialized(nodeDatabase)) {
      return DatabaseState.INITIALIZED;
    }

    return DatabaseState.INVALID;
  }
}
