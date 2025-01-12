package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodeexaminer.NodeDatabaseExaminer;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseStateAnalyser;
import ch.nolix.systemapi.noderawschemaapi.nodeexaminerapi.INodeDatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;

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
